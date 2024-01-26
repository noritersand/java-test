package builtin.wrapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * java.lang.Integer 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class IntegerTest {

    /**
     * -128 ~ 127 사이의 값은 상수로 사용되기 때문에 동등 비교(==) 가능. 나머지 범위는 .equals()를 사용해야 함
     */
    @Test
    void testEquals() {
        assertThat(Integer.valueOf(-1000).equals(Integer.valueOf(-1000))).isTrue();
        assertThat(Integer.valueOf(-129) != Integer.valueOf(-129)).isTrue();
        assertThat(Integer.valueOf(-128) == Integer.valueOf(-128)).isTrue();
        assertThat(Integer.valueOf(127) == Integer.valueOf(127)).isTrue();
        assertThat(Integer.valueOf(128) != Integer.valueOf(128)).isTrue();
        assertThat(Integer.valueOf(1000).equals(Integer.valueOf(1000))).isTrue();
    }

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
        assertThat("0").isEqualTo(Integer.toBinaryString(0));
        assertThat("1010").isEqualTo(Integer.toBinaryString(10));
        assertThat("11111111").isEqualTo(Integer.toBinaryString(255));
        assertThat("10000000000").isEqualTo(Integer.toBinaryString(1024));
        assertThat("10000000000000000").isEqualTo(Integer.toBinaryString(65536));
    }

    /**
     * 10진수를 8진수로 변환
     *
     * @author fixalot
     */
    @Test
    void convertToOctonary() {
        assertThat("0").isEqualTo(Integer.toOctalString(0));
        assertThat("12").isEqualTo(Integer.toOctalString(10));
        assertThat("377").isEqualTo(Integer.toOctalString(255));
        assertThat("2000").isEqualTo(Integer.toOctalString(1024));
        assertThat("200000").isEqualTo(Integer.toOctalString(65536));
    }

    /**
     * 10진수를 16진수로 변환
     *
     * @author fixalot
     */
    @Test
    void convertToHexadecimal() {
        assertThat("0").isEqualTo(Integer.toHexString(0));
        assertThat("a").isEqualTo(Integer.toHexString(10));
        assertThat("ff").isEqualTo(Integer.toHexString(255));
        assertThat("400").isEqualTo(Integer.toHexString(1024));
        assertThat("10000").isEqualTo(Integer.toHexString(65536));
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
