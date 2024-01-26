package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 최대공약수 구하기
 *
 * @author fixalot
 * @since 2017-05-30
 */
@Slf4j
class GreatestCommonDivisorTest {

    @Test
    void shouldEqual() {
        /*
         * input a input b 출력 169 104 -> 13 100 250 -> 50 1 1 -> 1 1000000 5 -> 5 104711 104717 -> 1 98304 65536 -> 32768
         */
        assertEquals(13, this.getGCD(169, 104));
        assertEquals(50, this.getGCD(100, 250));
        assertEquals(1, this.getGCD(1, 1));
        assertEquals(5, this.getGCD(1000000, 5));
        assertEquals(1, this.getGCD(104711, 104717));
        assertEquals(32768, this.getGCD(98304, 65536));
    }

    /**
     * 최대공약수 구하기
     *
     * @param a
     * @param b
     * @return
     * @author fixalot
     */
    private int getGCD(int a, int b) {
        int bigger = a;
        a = b > a ? b : a;
        int gcd = 0;
        for (int i = 1; i <= bigger; i++) {
            if (0 == a % i && 0 == b % i) {
                gcd = i;
            }
        }
        return gcd;
    }
}
