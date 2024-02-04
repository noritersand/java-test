package builtin.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2018-01-23
 */
@Slf4j
class MathTest {

    @Test
    void test() {
        for (int i = 0; i < 1000; ++i) {
            double random = Math.random();
            assertThat(String.valueOf(random).length()).isPositive().isGreaterThan(12).isLessThan(22);
        }
        for (int i = 0; i < 1000; ++i) {
            int random = (int) (Math.random() * 1000000);
            assertThat(String.valueOf(random).length()).isPositive()/*.isGreaterThan(0)*/.isLessThan(22);
        }

    }

    @Test
    void testAbs() {
        assertThat(Math.abs(123)).isEqualTo(123);
        assertThat(Math.abs(123)).isEqualTo(123);
        assertThat(Math.abs(0.1)).isEqualTo(0.1);
        assertThat(Math.abs(0.456)).isEqualTo(0.456);
    }
}
