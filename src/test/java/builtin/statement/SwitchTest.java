package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
    void whenVariableInitialize() {
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
                assertThat(b).isEqualTo(3);
                break;
        }
    }

    /**
     * switch의 인수가 null이면 어떻게 될까요?
     *
     * @author fixalot
     */
    @Test
    public void howAboutActualArgumentIsNull() {
        String imNull = null;
        assertThatThrownBy(() -> {
            switch (imNull) {
                case "a":
                    log.debug("it is 'a'");
                    break;
                default:
                    log.debug("it is null");
                    break;
            }
        }).isInstanceOf(NullPointerException.class);
    }

    /**
     * static final 제어자가 붙은 변수여도 실제 값이 런타임에 결정되면 case 표현식에 사용할 수 없다
     *
     * @author noritersand
     */
    @Test
    void shouldBeCompileError() {
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

    /**
     * <p>Java 13에 추가된 Yield</p>
     * <p>그리고 다중 case 레이블 + 화살표 블록(얘넨 언제 추가된건지 몲)</p>
     */
    @Test
    public void testYield() {
        var randomNames = new String [] {"Jayden", "Bernard", "Zino", "Mason", "Elvin"}[(int) (Math.random() * 5)];

        String name = switch(randomNames) {
           case "Jayden", "jayden" -> {
              System.out.println("Me!");
              yield "제이든";
           }
           case "Bernard", "bernard" -> "버나드";
           case "Zino" -> "자이노";
           case "Mason" -> "메이슨";
           case "Elvin" -> "엘빈";
           default -> "What's your name";
        };

//        log.debug("{}", name[0]);

        int result = switch ("abc") {
          case "abc", "bcd" -> { // 다중 레이블: "abc" or "bcd"와 일치하는지
              yield 1; // 1을 반환함
          } // 콜론(:) 대신 사용 가능한 화살표(->)와 중괄호({})
          default -> -1; // 표현식이 한 줄이면 람다처럼 괄호를 생략할 수 있음
        };
        assertThat(result).isEqualTo(1).isNotEqualTo(-1);
    }
}
