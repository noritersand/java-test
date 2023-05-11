package thirdparty.assertj.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>AssertJ 테스트 프레임웤의 기본적인 사용 방법을 작성함.</p>
 * <p>AssertJ는 JUnit에 의존하기 때문에 JUnit을 완전히 대체한다고는 할 수 없다.</p>
 */
public class AssertJTest {

    @Test
    public void usage() {
        String txt = "Hello" + "World!";
        assertThat(txt).isEqualTo("HelloWorld!");
        assertThat(txt).startsWithIgnoringCase("hello");

        assertThatThrownBy(() -> {
            int nan = 1 / 0;
        }).isInstanceOf(ArithmeticException.class).hasMessage("/ by zero");
    }
}
