package thirdparty.assertj.core;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>AssertJ 테스트 프레임웤의 기본적인 사용 방법을 작성함.</p>
 * <p>AssertJ는 JUnit에 의존하기 때문에 JUnit을 완전히 대체한다고는 할 수 없다.</p>
 */
@Slf4j
public class AssertJTest {

    @Test
    public void usage() {
        String txt = "Hello" + "World!";
        assertThat(txt).isEqualTo("HelloWorld!");
        assertThat(txt).startsWithIgnoringCase("hello");

        // Condition과 같이 사용하는 방법
        // 첫 번째 인자는 java.util.function.Predicate 인터페이스의 구현체
        // 두 번째 인자는 테스트에 실패했을 때 출력할 메시지(기대값에 대한 설명)
        assertThat(txt).is(new Condition<>(actual -> "HelloWorld!".equals(actual), "HelloWorld!"));

        assertThatThrownBy(() -> {
            int nan = 1 / 0;
        }).isInstanceOf(ArithmeticException.class).hasMessage("/ by zero");


    }
}
