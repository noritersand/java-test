package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class PrimitiveTest {

    @Test
    @SuppressWarnings("unused")
    public void useEscapeCharacter() {
        Byte bt = 123;
        byte bt2 = 123;
        Short st = 0;
        short st2 = 0;
        Integer it = 12345;
        int it2 = 12345;
        Long lg = 1234L;
        long lg2 = 1234L;
        Float flt = 1234.0F;
        float flt2 = 1234.0f;
        Double db = 1234.0D;
        double db2 = 1234.0d;
    }

    @Test
    void getMinMaxRange() {
        assertEquals(-128, Byte.MIN_VALUE);
        assertEquals(127, Byte.MAX_VALUE);
        assertEquals(-32768, Short.MIN_VALUE);
        assertEquals(32767, Short.MAX_VALUE);
        assertEquals(-2147483648, Integer.MIN_VALUE);
        assertEquals(2147483647, Integer.MAX_VALUE);
        assertEquals(-9223372036854775808L, Long.MIN_VALUE);
        assertEquals(9223372036854775807L, Long.MAX_VALUE);
        assertEquals("1.4E-45", String.valueOf(Float.MIN_VALUE));
        assertEquals("3.4028235E38", String.valueOf(Float.MAX_VALUE));
        assertEquals("4.9E-324", String.valueOf(Double.MIN_VALUE));
        assertEquals("1.7976931348623157E308", String.valueOf(Double.MAX_VALUE));
    }

    @Test
    void autoBoxing() {
        assertEquals(1, (long) Long.valueOf(1));
    }

    @Test
    void testValueOf() {
        int number = Integer.valueOf("1");
        assertEquals(1, number);

        assertThrows(NumberFormatException.class, () -> {
            Integer.valueOf(""); // 빈 문자열은 valueOf 불가
        });

        assertThrows(NumberFormatException.class, () -> {
            Integer.valueOf(null); // null은 valueOf 불가
        });
    }

    @Test
    void floatCalculateErrorRange() {
        double val1 = 1.5;
        double val2 = 0.3;

        // 오차 허용 안함
        assertEquals(0.44999999999999996, val1 * val2, 0);

        // 0.0000000000000001까지 오차 허용
        assertEquals(0.45, val1 * val2, 0.0000000000000001);
    }
}
