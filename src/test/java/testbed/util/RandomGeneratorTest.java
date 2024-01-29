package testbed.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RandomeGenerator 테스트 슈트
 *
 * @author fixalot
 * @since 2024-01-29
 */
class RandomGeneratorTest {

    @Test
    void testGenerateFixedLengthRandomDecimal() {
        for (int i = 0; i < 100; ++i) {
            var length = 6;
            String numbers = RandomGenerator.generateFixedLengthRandomDecimal(length);
            assertThat(numbers).hasSize(length);
        }
        for (int i = 0; i < 100; ++i) {
            var length = 8;
            String numbers = RandomGenerator.generateFixedLengthRandomDecimal(length);
            assertThat(numbers).hasSize(length);
        }
    }
}
