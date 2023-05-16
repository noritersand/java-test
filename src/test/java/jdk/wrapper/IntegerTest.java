package jdk.wrapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

/**
 * java.lang.Integer 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class IntegerTest {

    @Test
    void testMinMax() {
        assertThat(Integer.MAX_VALUE).isEqualTo(2147483647);
        assertThat(Integer.MIN_VALUE).isEqualTo(-2147483648);
    }

    /**
     * 10진수를 2진수로 변환
     *
     * @author fixalot
     */
    @Test
    void convertToBinary() {
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
    void convertToOctonary() {
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
    void convertToHexadecimal() {
        assertEquals("0", Integer.toHexString(0));
        assertEquals("a", Integer.toHexString(10));
        assertEquals("ff", Integer.toHexString(255));
        assertEquals("400", Integer.toHexString(1024));
        assertEquals("10000", Integer.toHexString(65536));
    }

    /**
     * 타입 변환 메서드는 nullSafe 하지 않음
     */
    @Test
    void shouldBeError() {
        String str = "";
        assertThatThrownBy(() -> {
            Integer.parseInt(str);
        }).isInstanceOf(NumberFormatException.class);
        assertThatThrownBy(() -> {
            Integer.valueOf(str);
        }).isInstanceOf(NumberFormatException.class);
    }
}
