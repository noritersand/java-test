package builtin.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author noritersand
 * @since 2017-07-27
 */
@Slf4j
class BigDecimalTest {

    @Test
    void instantiate() {
        assertThat(new BigDecimal("123")).isEqualTo(BigDecimal.valueOf(123));
    }

    /**
     * 어느쪽이 크거나 작은지 비교는 compareTo
     *
     * @author noritersand
     */
    @Test
    void testCompareTo() {
        assertThat(BigDecimal.ZERO.compareTo(BigDecimal.ZERO)).isEqualTo(0); // a.compareTo(b)에서 0이면 a와 b가 같음
        assertThat(BigDecimal.ZERO.compareTo(BigDecimal.ONE)).isEqualTo(-1); // a.compareTo(b)에서 -1이면 a가 b보다 작음
        assertThat(BigDecimal.ZERO.compareTo(new BigDecimal(-1))).isEqualTo(1); // a.compareTo(b)에서 1이면 a가 b보다 큼
    }

    /**
     * 값이 같은지 비교하는건 compareTo 안써도 됨.
     *
     * @author noritersand
     */
    @Test
    void testEquals() {
        assertThat(new BigDecimal("0")).isEqualTo(BigDecimal.ZERO);
        assertThat(new BigDecimal("0.0")).isNotEqualTo(BigDecimal.ZERO); // 0.0과 0은 같지 않음.

        assertThat(new BigDecimal("1")).isEqualTo(BigDecimal.ONE);
        assertThat(new BigDecimal("1.0")).isNotEqualTo(BigDecimal.ONE); // 1.1과 1은 같지 않음.

        assertThat(new BigDecimal("123.1")).isEqualTo(new BigDecimal("123.1"));
        assertThat(new BigDecimal("123.1")).isNotSameAs(new BigDecimal("123.1")); // 값은 같아도 다른 인스턴스
    }

    /**
     * 절대값: 양수나 음수를 모두 양수로
     *
     * @author noritersand
     */
    @Test
    void testAbs() {
        assertThat(new BigDecimal("-0.1").abs()).isEqualTo(new BigDecimal("0.1"));
        assertThat(new BigDecimal("1.6").abs()).isEqualTo(new BigDecimal("1.6"));
        assertThat(new BigDecimal("-10.592834").abs()).isEqualTo(new BigDecimal("10.592834"));
    }

    @Test
    void getConst() {
        assertThat(new BigDecimal(0)).isEqualTo(BigDecimal.ZERO);
        assertThat(new BigDecimal(1)).isEqualTo(BigDecimal.ONE);
        assertThat(new BigDecimal(10)).isEqualTo(BigDecimal.TEN);
    }

    /**
     * 사칙 연산자
     *
     * @author fixalot
     */
    @Test
    void useOperator() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("20");

        assertThat(a.add(b)).isEqualTo(new BigDecimal("30"));
        assertThat(b.subtract(a)).isEqualTo(new BigDecimal("10"));
        assertThat(a.multiply(b)).isEqualTo(new BigDecimal("200"));
        assertThat(b.divide(a)).isEqualTo(new BigDecimal("2"));
    }

    /**
     * 나누기 할 땐 반드시 범위를 지정해야 함. 중요.
     *
     * @author fixalot
     */
    @Test
    void testDivide() {
        BigDecimal smallOne = new BigDecimal("64000.00");
        BigDecimal bigOne = new BigDecimal("69000.00");

        // 그냥 나눠버리면 BigDecimal도 감당 못하는 결과가 나옴
        assertThatThrownBy(() -> smallOne.divide(bigOne)).isInstanceOf(ArithmeticException.class);

        // 소수점 셋 째 자리에서 반올림
        BigDecimal result = smallOne.divide(bigOne, 2, RoundingMode.HALF_UP);
        assertThat(result).isEqualTo(new BigDecimal("0.93"));
    }

    /**
     * 퍼센테이지 계산
     *
     * @author fixalot
     */
    @Test
    void calculatePercentage() {
        BigDecimal smallOne = new BigDecimal("2000");
        BigDecimal bigOne = new BigDecimal("8000");
        BigDecimal percentage = new BigDecimal("25");

        assertThat(smallOne.divide(bigOne).multiply(new BigDecimal(100)).intValue()).isEqualTo(25);
        assertThat(bigOne.multiply(percentage).divide(new BigDecimal(100)).intValue()).isEqualTo(2000);
    }

    /**
     * 정밀도는 표현가능한 자릿수를 의미함.
     *
     * @author noritersand
     */
    @Test
    void testPrecision() {
        BigDecimal n = new BigDecimal("1.003");
        assertThat(n.precision()).isEqualTo(4);
    }

    /**
     * 스케일은 소숫점 이하 자릿수를 의미함.
     *
     * @author noritersand
     */
    @Test
    void testScale() {
        BigDecimal n = new BigDecimal("1.003");
        assertThat(n.scale()).isEqualTo(3);
    }

    /**
     * scale 설정: 특정 소수점 이하 반올림, 버림, 내림
     *
     * @author noritersand
     */
    @Test
    void testSetScale() {
        // 반올림
        BigDecimal roundMe = new BigDecimal("12.536512304");
        assertThat(roundMe.setScale(2, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("12.54"));
        assertThat(roundMe.setScale(3, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("12.537"));
        assertThat(roundMe.setScale(4, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("12.5365"));

        // 올림
        BigDecimal ceilMe = new BigDecimal("12.536512304");
        assertThat(ceilMe.setScale(2, RoundingMode.CEILING)).isEqualTo(new BigDecimal("12.54"));
        assertThat(ceilMe.setScale(3, RoundingMode.CEILING)).isEqualTo(new BigDecimal("12.537"));
        assertThat(ceilMe.setScale(4, RoundingMode.CEILING)).isEqualTo(new BigDecimal("12.5366"));

        // 내림(=버림, 절삭)
        BigDecimal floorMe = new BigDecimal("12.536512304");
        assertThat(floorMe.setScale(2, RoundingMode.FLOOR)).isEqualTo(new BigDecimal("12.53"));
        assertThat(floorMe.setScale(3, RoundingMode.FLOOR)).isEqualTo(new BigDecimal("12.536"));
        assertThat(floorMe.setScale(4, RoundingMode.FLOOR)).isEqualTo(new BigDecimal("12.5365"));
    }

    /**
     * 정수 1의 자리를 올림하기
     */
    @Test
    void ceilingOnesPlace() {
        assertThat(roundUpToTen(new BigDecimal("1"))).isEqualTo(new BigDecimal("10"));
        assertThat(roundUpToTen(new BigDecimal("7"))).isEqualTo(new BigDecimal("10"));
        assertThat(roundUpToTen(new BigDecimal("99"))).isEqualTo(new BigDecimal("100"));
        assertThat(roundUpToTen(new BigDecimal("321"))).isEqualTo(new BigDecimal("330"));
        assertThat(roundUpToTen(new BigDecimal("12345"))).isEqualTo(new BigDecimal("12350"));
        assertThat(roundUpToTen(new BigDecimal("98283"))).isEqualTo(new BigDecimal("98290"));
        assertThat(roundUpToTen(new BigDecimal("55000"))).isEqualTo(new BigDecimal("55000"));
        assertThat(roundUpToTen(new BigDecimal("999000"))).isEqualTo(new BigDecimal("999000"));

        assertThat(roundUpToTen2(new BigDecimal("1"))).isEqualTo(new BigDecimal("10"));
        assertThat(roundUpToTen2(new BigDecimal("7"))).isEqualTo(new BigDecimal("10"));
        assertThat(roundUpToTen2(new BigDecimal("99"))).isEqualTo(new BigDecimal("100"));
        assertThat(roundUpToTen2(new BigDecimal("321"))).isEqualTo(new BigDecimal("330"));
        assertThat(roundUpToTen2(new BigDecimal("12345"))).isEqualTo(new BigDecimal("12350"));
        assertThat(roundUpToTen2(new BigDecimal("98283"))).isEqualTo(new BigDecimal("98290"));
        assertThat(roundUpToTen2(new BigDecimal("55000"))).isEqualTo(new BigDecimal("55000"));
        assertThat(roundUpToTen2(new BigDecimal("999000"))).isEqualTo(new BigDecimal("999000"));
    }

    /**
     * 1의 자리에서 올림: 1의 자리를 제거한 뒤 0보다 크면 그 값을 더하는 방식
     *
     * @param n
     * @return
     */
    private BigDecimal roundUpToTen(BigDecimal n) {
        BigDecimal lastDigit = n.remainder(BigDecimal.TEN);

        if (lastDigit.compareTo(BigDecimal.ZERO) > 0) {
            n = n.add(BigDecimal.TEN.subtract(lastDigit));
        }
        return n;
    }

    /**
     * 1의 자리에서 올림: n을 10으로 나누어 몫을 올림 처리하고, 다시 10으로 곱하는 방식
     *
     * @param n
     * @return
     */
    private BigDecimal roundUpToTen2(BigDecimal n) {
        // 정수 부분만 가져오기
        BigDecimal integerPart = n.setScale(0, RoundingMode.CEILING);

        // 1의 자리 올림 처리
        return integerPart.divide(BigDecimal.TEN, 0, RoundingMode.CEILING)
                .multiply(BigDecimal.TEN);
    }

    /**
     * 10의 단위로 스케일링(소수점 조절)
     *
     * @author fixalot
     */
    @Test
    void testScaleByPowerOfTen() {
        assertThat(new BigDecimal("120.345").scaleByPowerOfTen(-2).doubleValue()).isEqualTo(1.20345D);
        assertThat(new BigDecimal("120.345").scaleByPowerOfTen(2).doubleValue()).isEqualTo(12034.5D);
    }

    /**
     * 소수점 좌측 이동
     *
     * @author fixalot
     */
    @Test
    void testMovePointLeft() {
        assertThat(new BigDecimal(100).movePointLeft(1).doubleValue()).isEqualTo(10.0D);
    }

    /**
     * 소수점 우측 이동
     *
     * @author fixalot
     */
    @Test
    void testMovePointRight() {
        assertThat(new BigDecimal(100).movePointRight(1).doubleValue()).isEqualTo(1000.0D);
    }

    /**
     * double은 소수점 계산에서 오차 발생함.
     *
     * @author fixalot
     */
    @Test
    void calculateError() {
        double val1 = 1.5;
        double val2 = 0.3;
        BigDecimal bigVal1 = new BigDecimal("1.5");
        BigDecimal bigVal2 = new BigDecimal("0.3");

        assertThat(val1 * val2).isEqualTo(0.44999999999999996D);
        assertThat(bigVal1.multiply(bigVal2)).isEqualTo(new BigDecimal("0.45"));
    }


    /**
     * 소수점을 없애고 BigInteger로 변환
     *
     * @author noritersand
     */
    @Test
    void testUnscaledValue() {
        assertThat(new BigDecimal("0.1").unscaledValue()).isEqualTo(BigInteger.ONE);
        assertThat(new BigDecimal("1.6").unscaledValue()).isEqualTo(new BigInteger("16"));
        assertThat(new BigDecimal("10.592834").unscaledValue()).isEqualTo(new BigInteger("10592834"));
    }

    @Test
    void testToBigInteger() {
        // BigInteger로 변환시 소수점 이하는 버린다.
        assertThat(new BigDecimal("2.123").toBigInteger()).isEqualTo(BigInteger.TWO);
        assertThat(new BigDecimal("2.723").toBigInteger()).isEqualTo(BigInteger.TWO);

        // BigInteger와 BigDecimal은 비교/연산할 수 없다.
//		BigInteger.ONE.equals(BigDecimal.ONE); 
//		BigInteger.ONE.compareTo(BigDecimal.ONE);
//		BigInteger.ONE.add(BigDecimal.ONE);
    }
}
