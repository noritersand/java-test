package jdk.java.lang;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * java.lang.Integer 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
public class IntegerTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(IntegerTest.class);

    /**
     * 10진수를 2진수로 변환
     *
     * @author fixalot
     */
    @Test
    public void convertToBinary() {
        assertEquals("0", Integer.toBinaryString(0));
        assertEquals("1010", Integer.toBinaryString(10));
        assertEquals("11111111", Integer.toBinaryString(255));
        assertEquals("10000000000", Integer.toBinaryString(1024));
        assertEquals("10000000000000000", Integer.toBinaryString(65536));
    }

    /**
     * 10진수를 8진수로 변환
     *
     * @author fixalot
     */
    @Test
    public void convertToOctonary() {
        assertEquals("0", Integer.toOctalString(0));
        assertEquals("12", Integer.toOctalString(10));
        assertEquals("377", Integer.toOctalString(255));
        assertEquals("2000", Integer.toOctalString(1024));
        assertEquals("200000", Integer.toOctalString(65536));
    }

    /**
     * 10진수를 16진수로 변환
     *
     * @author fixalot
     */
    @Test
    public void convertToHexadecimal() {
        assertEquals("0", Integer.toHexString(0));
        assertEquals("a", Integer.toHexString(10));
        assertEquals("ff", Integer.toHexString(255));
        assertEquals("400", Integer.toHexString(1024));
        assertEquals("10000", Integer.toHexString(65536));
    }
}
