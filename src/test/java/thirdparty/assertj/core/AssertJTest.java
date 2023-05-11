package thirdparty.assertj.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>JUnit 대체제인 AssertJ 사용 방법</p>
 */
public class AssertJTest {

    @Test
    public void usage() {
        String txt = "Hello" + "World!";
        assertThat(txt).isEqualTo("HelloWorld!");

        assertThatThrownBy(() -> {
            int nan = 1 / 0;
        }).isInstanceOf(ArithmeticException.class).hasMessage("/ by zero");
    }
}
