package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class SwitchTest {

    /**
     * switch 안의 로컬 변수는 마치 자바스크립트의 끌어올림처럼 작동함.
     *
     * @author fixalot
     */
    @Test
    public void whenVariableInitialize() {
        int a = 2;
        switch (a) {
            case 1:
                int b = 0; // 여기는 오지 않는걸로 보이지만
                log.debug("{}", b);
                break;
            case 2:
//				b; // 이렇게 하면 컴파일 에러: Syntax error, insert "VariableDeclarators" to complete LocalVariableDeclaration
                b = 3;
                log.debug("{}", b);
                assertEquals(3, b);
                break;
        }
    }

    /**
     * switch의 인수가 null이면 어떻게 될까요?
     *
     * @author fixalot
     */
    @Test
    @SuppressWarnings("null")
    public void howAboutActualArgumentIsNull() {
        String imNull = null;
        try {
            switch (imNull) {
                case "a":
                    log.debug("it is 'a'");
                    break;
                default:
                    log.debug("it is null");
                    break;
            }
            assertTrue(1 == 2);
        } catch (NullPointerException e) {
            log.debug("에러 났습니당.");
            assertNotEquals(1, 2);
        }
    }

    /**
     * static final 제어자가 붙은 변수여도 실제 값이 런타임에 결정되면 case 표현식에 사용할 수 없다
     *
     * @author noritersand
     */
    @Test
    public void shouldBeCompileError() {
        if (1 == SwitchTest.myS) {//			case (myS + 0): // case expressions must be constant expressions
//			case (myS + 1): // case expressions must be constant expressions
            log.debug("정상값 입력");
        } else {
            log.debug("허용 수치를 벗어남");
        }
    }

    public static final int myS = getData();

    public static final int getData() {
        return 1;
    }
}
