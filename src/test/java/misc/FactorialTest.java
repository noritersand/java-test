package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 팩토리얼 테스트
 *
 * @author fixalot
 * @since 2018-10-31
 */
@Slf4j
class FactorialTest {

    @Test
    void test() {
        // 4! = 4 x 3 x 2 x 1 = 24
        // 4! = 4 x 3 x 2! = 24
        // 4! = 4 x 3! = 24
        // 4! = 4! = 24

        assertEquals(1, factorial(1));
        assertEquals(2, factorial(2));
        assertEquals(6, factorial(3));
        assertEquals(24, factorial(4));
        assertEquals(120, factorial(5));
    }

    public int factorial(int n) {
        if (1 < n) {
            return n * factorial(n - 1); // 재귀호출
        } else {
            return 1;
        }
    }
}
