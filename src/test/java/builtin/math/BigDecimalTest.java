package builtin.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author noritersand
 * @since 2017-07-27
 */
@Slf4j
public class BigDecimalTest {

    @Test
    void instantiate() {
        assertEquals(BigDecimal.valueOf(123), new BigDecimal("123"));
    }

    /**
     * 어느쪽이 크거나 작은지 비교는 compareTo
     *
     * @author noritersand
     */
    @Test
    void testCompareTo() {
        assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimal.ZERO)); // a.compareTo(b)에서 0이면 a와 b가 같음
        assertEquals(-1, BigDecimal.ZERO.compareTo(BigDecimal.ONE)); // a.compareTo(b)에서 -1이면 a가 b보다 작음
        assertEquals(1, BigDecimal.ZERO.compareTo(new BigDecimal(-1))); // a.compareTo(b)에서 1이면 a가 b보다 큼
    }

    /**
     * 값이 같은지 비교하는건 compareTo 안써도 됨.
     *
     * @author noritersand
     */
    @Test
    void testEquals() {
        assertTrue(new BigDecimal("0").equals(BigDecimal.ZERO));
        assertFalse(new BigDecimal("0.0").equals(BigDecimal.ZERO)); // 0.0과 0은 같지 않음.
        assertTrue(new BigDecimal("1").equals(BigDecimal.ONE));
        assertFalse(new BigDecimal("1.0").equals(BigDecimal.ONE)); // 1.1과 1은 같지 않음.
        assertTrue(new BigDecimal("123.1").equals(new BigDecimal("123.1")));
    }

    /**
     * 절대값: 양수나 음수를 모두 양수로
     *
     * @author noritersand
     */
    @Test
    void testAbs() {
        assertEquals(new BigDecimal("0.1"), new BigDecimal("-0.1").abs());
        assertEquals(new BigDecimal("1.6"), new BigDecimal("1.6").abs());
        assertEquals(new BigDecimal("10.592834"), new BigDecimal("-10.592834").abs());
    }

    @Test
    void getConst() {
        assertEquals(BigDecimal.ZERO, new BigDecimal(0));
        assertEquals(BigDecimal.ONE, new BigDecimal(1));
        assertEquals(BigDecimal.TEN, new BigDecimal(10));
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

        assertEquals(new BigDecimal("30"), a.add(b));
        assertEquals(new BigDecimal("10"), b.subtract(a));
        assertEquals(new BigDecimal("200"), a.multiply(b));
        assertEquals(new BigDecimal("2"), b.divide(a));
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
        assertThrows(ArithmeticException.class, () -> smallOne.divide(bigOne));

        // 소수점 셋 째 자리에서 반올림
        BigDecimal result = smallOne.divide(bigOne, 2, RoundingMode.HALF_UP);
        assertEquals(new BigDecimal("0.93"), result);
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

        assertEquals(25, smallOne.divide(bigOne).multiply(new BigDecimal(100)).intValue());
        assertEquals(2000, bigOne.multiply(percentage).divide(new BigDecimal(100)).intValue());
    }

    /**
     * 정밀도는 표현가능한 자릿수를 의미함.
     *
     * @author noritersand
     */
    @Test
    void testPrecision() {
        BigDecimal n = new BigDecimal("1.003");
        assertEquals(4, n.precision());
    }

    /**
     * 스케일은 소숫점 이하 자릿수를 의미함.
     *
     * @author noritersand
     */
    @Test
    void testScale() {
        BigDecimal n = new BigDecimal("1.003");
        assertEquals(3, n.scale());
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
        assertEquals(new BigDecimal("12.54"), roundMe.setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("12.537"), roundMe.setScale(3, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("12.5365"), roundMe.setScale(4, RoundingMode.HALF_UP));

        // 올림
        BigDecimal ceilMe = new BigDecimal("12.536512304");
        assertEquals(new BigDecimal("12.54"), ceilMe.setScale(2, RoundingMode.CEILING));
        assertEquals(new BigDecimal("12.537"), ceilMe.setScale(3, RoundingMode.CEILING));
        assertEquals(new BigDecimal("12.5366"), ceilMe.setScale(4, RoundingMode.CEILING));

        // 내림(=버림, 절삭)
        BigDecimal floorMe = new BigDecimal("12.536512304");
        assertEquals(new BigDecimal("12.53"), floorMe.setScale(2, RoundingMode.FLOOR));
        assertEquals(new BigDecimal("12.536"), floorMe.setScale(3, RoundingMode.FLOOR));
        assertEquals(new BigDecimal("12.5365"), floorMe.setScale(4, RoundingMode.FLOOR));
    }

    /**
     * 10의 단위로 스케일링(소수점 조절)
     *
     * @author fixalot
     */
    @Test
    void testScaleByPowerOfTen() {
        assertEquals(1.20345D, new BigDecimal("120.345").scaleByPowerOfTen(-2).doubleValue(), 0);
        assertEquals(12034.5D, new BigDecimal("120.345").scaleByPowerOfTen(2).doubleValue(), 0);
    }

    /**
     * 소수점 좌측 이동
     *
     * @author fixalot
     */
    @Test
    void testMovePointLeft() {
        assertEquals(10.0D, new BigDecimal(100).movePointLeft(1).doubleValue(), 0);
    }

    /**
     * 소수점 우측 이동
     *
     * @author fixalot
     */
    @Test
    void testMovePointRight() {
        assertEquals(1000.0D, new BigDecimal(100).movePointRight(1).doubleValue(), 0);
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

        assertEquals(0.44999999999999996D, val1 * val2, 0);
        assertEquals(new BigDecimal("0.45"), bigVal1.multiply(bigVal2));
    }


    /**
     * 소수점을 없애고 BigInteger로 변환
     *
     * @author noritersand
     */
    @Test
    void testUnscaledValue() {
        assertEquals(BigInteger.ONE, new BigDecimal("0.1").unscaledValue());
        assertEquals(new BigInteger("16"), new BigDecimal("1.6").unscaledValue());
        assertEquals(new BigInteger("10592834"), new BigDecimal("10.592834").unscaledValue());
    }

    @Test
    void testToBigInteger() {
        // BigInteger로 변환시 소수점 이하는 버린다.
        assertEquals(BigInteger.TWO, new BigDecimal("2.123").toBigInteger());
        assertEquals(BigInteger.TWO, new BigDecimal("2.723").toBigInteger());

        // BigInteger와 BigDecimal은 비교/연산할 수 없다.
//		BigInteger.ONE.equals(BigDecimal.ONE); 
//		BigInteger.ONE.compareTo(BigDecimal.ONE);
//		BigInteger.ONE.add(BigDecimal.ONE);
    }
}
