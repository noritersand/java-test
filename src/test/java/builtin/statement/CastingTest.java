package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 캐스팅 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class CastingTest {

    /**
     * 다른 타입으로 변환하기
     */
    @Test
    void typeCasting() {
        int intV = 1;
//        Double doubleValue = integerValue; // 이 코드는 컴파일 에러가 발생함
        double doubV = intV; // 캐스팅 표현식 없이 타입 캐스팅 가능
        assertEquals(1.0, doubV);

        double doubV2 = 5.5;
        int intV2 = (int) doubV2; // 이 경우엔 생략할 수 없음
        assertEquals(5, intV2);
    }

    /**
     * 타입 캐스팅 하려는 값이 null이라도 런타임 에러는 발생하지 않음.
     */
    @Test
    void castWithNull() {
        Object imnotnotnull = null;
        String string = (String) imnotnotnull;
        assertNull(string);

        Integer integer = (Integer) imnotnotnull;
        assertNull(integer);
    }

    @Test
    void upCasting() {
        Child c = new Child();
        Parent p = c;
        assertEquals("Child", p.getName());
//		p.play(); // undefined method
    }

    @Test
    void downCasting() {
        // 요로케는 안됨.
        assertThrows(ClassCastException.class, () -> {
            Parent p = new Parent();
            @SuppressWarnings("unused")
            Child c = (Child) p;
        });

        // 요로케는 됨.
        Parent p = new Child();
        Child c = (Child) p;

        assertEquals("Child", c.getName());
        c.play();
    }
    private class Parent {
        public String getName() {
            return "Parent";
        }

    }
    private class Child extends Parent {

        public void play() {
            log.debug("uoo-hoo!");
        }
        @Override
        public String getName() {
            return "Child";
        }

    }
}
