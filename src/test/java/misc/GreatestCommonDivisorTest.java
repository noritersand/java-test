package misc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 최대공약수 구하기
 *
 * @author fixalot
 * @since 2017-05-30
 */
public class GreatestCommonDivisorTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(GreatestCommonDivisorTest.class);

    @Test
    public void shouldEqual() {
        /*
         * input a input b 출력 169 104 -> 13 100 250 -> 50 1 1 -> 1 1000000 5 -> 5 104711 104717 -> 1 98304 65536 -> 32768
         */
        assertEquals(13, getGCD(169, 104));
        assertEquals(50, getGCD(100, 250));
        assertEquals(1, getGCD(1, 1));
        assertEquals(5, getGCD(1000000, 5));
        assertEquals(1, getGCD(104711, 104717));
        assertEquals(32768, getGCD(98304, 65536));
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
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }
}
