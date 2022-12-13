package jdk.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class GenericMethodTest {

    @Test
    public void shouldSuccess() {
        CustomGeneric gen = new CustomGeneric();

        gen.getSome(new LittleDecimal(123)); // correct

        List<LittleDecimal> list = List.of(new LittleDecimal(123));
        gen.getSome2(list); // correct

        gen.getSome3(new LittleDecimal(0));
        gen.getSome3(new TinyDecimal(123));


    }

    @Test
    public void shouldBeCompileError() {
//		CustomGeneric gen = new CustomGeneric();

//		gen.getSome("123"); // wrong
        // The method getSome(T) in the type GenericMethodTest.CustomGeneric is not applicable for the arguments (String)

//		gen.getSome(123); // wrong
        // The method getSome(T) in the type GenericMethodTest.CustomGeneric is not applicable for the arguments (int)

//		List<Integer> list = Arrays.asList(new Integer[] { Integer.valueOf(123) });
//		gen.getSome2(list); // wrong
        // The method getSome2(List<? super GenericMethodTest.TinyDecimal>) in the type GenericMethodTest.CustomGeneric is not applicable for the arguments (List<Integer>)

//		gen.getSome3("123"); // 컴파일 에러
        // The method getSome3(T) in the type GenericMethodTest.CustomGeneric is not applicable for the arguments (String)
    }

    private class CustomGeneric {
        public <T extends BigDecimal> void getSome(T number) {
            log.debug("{}", number);
        }

        public void getSome2(List<? super TinyDecimal> number) {
            log.debug("{}", number);
        }

        public <T extends BigDecimal> void getSome3(T a) {
            log.debug("{}", a);
        }
    }

    private class LittleDecimal extends BigDecimal {
        private static final long serialVersionUID = 2718457985045593298L;

        public LittleDecimal(int val) {
            super(val);
        }
    }

    private class TinyDecimal extends LittleDecimal {
        private static final long serialVersionUID = -6691378068791156837L;

        public TinyDecimal(int val) {
            super(val);
        }
    }
}
