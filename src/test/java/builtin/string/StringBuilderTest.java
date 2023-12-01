package builtin.string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * {@link StringBuilder} 테스트 슈트
 */
public class StringBuilderTest {

    @Test
    void basicUsage() {
        StringBuilder builder = new StringBuilder().append("a");
        builder.append("b");
        builder.append("c");
        builder.append("d");;
        String str = builder.toString();
        assertThat(str).isEqualTo("abcd");
    }

    @Test
    void testIDERecommendation() {
        StringBuilder builder = new StringBuilder()
                .append("a")
                .append("b")
                .append("c")
                .append("d");
        builder.append("e");
        assertThat(builder.toString()).isEqualTo("abcde");
    }
}
