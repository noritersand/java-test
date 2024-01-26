package builtin.newfeature.thirteen;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SwitchYieldTest {

        /**
     * <p>Java 13에 추가된 Yield</p>
     * <p>그리고 다중 case 레이블 + 화살표 블록(얘넨 언제 추가된건지 몲)</p>
     */
    @Test
    void testYield() {
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
