package builtin.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

/**
 * JDK BigInteger 테스트
 *
 * @author noritersand
 * @since 2019-12-16
 */
@Slf4j
class BigIntegerTest {

    @Test
    void instantiate() {
        BigInteger actual1 = new BigInteger("123");
        BigInteger actual2 = BigInteger.valueOf(123);
        assertThat(actual2).isEqualTo(actual1).isNotSameAs(actual1);

        assertThatThrownBy(() -> {
            new BigInteger("123.456");
        }).isInstanceOf(NumberFormatException.class).hasMessage("For input string: \"123.456\"");
    }

    @Test
    void testMax() {
        BigInteger oneTwoThree = new BigInteger("123");
        BigInteger fourFiveSix = new BigInteger("456");
        assertThat(oneTwoThree.max(fourFiveSix))
                .isEqualTo(fourFiveSix)
                .isSameAs(fourFiveSix);
    }

    @Test
    void fromBigDecimal() {
        // BigInteger로 변환시 소수점 이하는 버린다.
        assertThat(new BigDecimal("2.123").toBigInteger()).isEqualTo(BigInteger.TWO);
        assertThat(new BigDecimal("2.723").toBigInteger()).isEqualTo(BigInteger.TWO);
    }

    @Test
    void toBigDecimal() {
        BigInteger one = new BigInteger("1");
        BigInteger ten = new BigInteger("10");
        assertThat(new BigDecimal(one)).isEqualTo(BigDecimal.ONE);
        assertThat(new BigDecimal(ten)).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void arithmeticOperation() {
        BigInteger ten = BigInteger.TEN;
        BigInteger two = BigInteger.TWO;
        assertThat(ten.add(two)).isEqualTo(BigInteger.valueOf(12));
        assertThat(ten.subtract(two)).isEqualTo(BigInteger.valueOf(8));
        assertThat(ten.multiply(two)).isEqualTo(BigInteger.valueOf(20));
        assertThat(ten.divide(two)).isEqualTo(BigInteger.valueOf(5));
    }

    @Test
    void logicalOperation() {
        BigInteger zero = BigInteger.ZERO;
        BigInteger one = BigInteger.ONE;
        assertThat(zero.not().toString()).isEqualTo("-1");
        assertThat(zero.and(one)).isEqualTo(BigInteger.ZERO);
        assertThat(zero.andNot(one)).isEqualTo(BigInteger.ZERO);
        assertThat(zero.or(one)).isEqualTo(BigInteger.ONE);
        assertThat(zero.xor(one)).isEqualTo(BigInteger.ONE);
    }
}
