package jdk.java.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author noritersand
 */
public class BigDecimalTest {
	private static final Logger logger = LoggerFactory.getLogger(BigDecimalTest.class);

	@Test
	public void getConst() {
		Assert.assertEquals(new BigDecimal(0), BigDecimal.ZERO);
		Assert.assertEquals(new BigDecimal(1), BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(10), BigDecimal.TEN);
	}

	/**
	 * 사칙 연산자
	 * 
	 * @author fixalot
	 */
	@Test
	public void useOperator() {
		BigDecimal a = new BigDecimal("10");
		BigDecimal b = new BigDecimal("20");

		Assert.assertEquals(new BigDecimal("30"), a.add(b));
		Assert.assertEquals(new BigDecimal("10"), b.subtract(a));
		Assert.assertEquals(new BigDecimal("200"), a.multiply(b));
		Assert.assertEquals(new BigDecimal("2"), b.divide(a));
	}

	/**
	 * 나누기 할 땐 반드시 범위를 지정해야 함. 중요.
	 * 
	 * @author fixalot
	 */
	@Test
	public void testDivide() {
		BigDecimal smallOne = new BigDecimal("64000.00");
		BigDecimal bigOne = new BigDecimal("69000.00");

		try {
			// 그냥 나눠버리면 BigDecimal도 감당 못하는 결과가 나옴
			smallOne.divide(bigOne);
		} catch (ArithmeticException e) {
			logger.debug("에러 난다요.");
		}
		// 소수점 셋 째 자리에서 반올림
		BigDecimal result = smallOne.divide(bigOne, 2, RoundingMode.HALF_UP);
		Assert.assertEquals(new BigDecimal("0.93"), result);
	}

	/**
	 * 퍼센테이지 계산
	 * 
	 * @author fixalot
	 */
	@Test
	public void calculatePercentage() {
		BigDecimal smallOne = new BigDecimal("2000");
		BigDecimal bigOne = new BigDecimal("8000");
		BigDecimal percentage = new BigDecimal("25");

		Assert.assertEquals(25, smallOne.divide(bigOne).multiply(new BigDecimal(100)).intValue());
		Assert.assertEquals(2000, bigOne.multiply(percentage).divide(new BigDecimal(100)).intValue());
	}

	/**
	 * 10의 단위로 스케일링(소수점 조절)
	 * 
	 * @author fixalot
	 */
	@Test
	public void testScaleByPowerOfTen() {
		Assert.assertEquals(1.20345D, new BigDecimal(120.345).scaleByPowerOfTen(-2).doubleValue(), 0);
		Assert.assertEquals(12034.5D, new BigDecimal(120.345).scaleByPowerOfTen(2).doubleValue(), 0);
	}

	/**
	 * 소수점 좌측 이동
	 * 
	 * @author fixalot
	 */
	@Test
	public void testMovePointLeft() {
		Assert.assertEquals(10.0D, new BigDecimal(100).movePointLeft(1).doubleValue(), 0);
	}

	/**
	 * 소수점 우측 이동
	 * 
	 * @author fixalot
	 */
	@Test
	public void testMovePointRight() {
		Assert.assertEquals(1000D, new BigDecimal(100).movePointRight(1).doubleValue(), 0);
	}

	/**
	 * 어느쪽이 크거나 작은지 비교는 compareTo
	 * 
	 * @author noritersand
	 */
	@Test
	public void testCompareTo() {
		Assert.assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimal.ZERO)); // a.compareTo(b)에서 0이면 a와 b가 같음
		Assert.assertEquals(-1, BigDecimal.ZERO.compareTo(BigDecimal.ONE)); // a.compareTo(b)에서 -1이면 a가 b보다 작음
		Assert.assertEquals(1, BigDecimal.ZERO.compareTo(new BigDecimal(-1))); // a.compareTo(b)에서 1이면 a가 b보다 큼
	}
	
	/**
	 * 값이 같은지 비교하는건 compareTo 안써도 됨.
	 * 
	 * @author noritersand
	 */
	@Test
	public void testEquals() {
		Assert.assertTrue(new BigDecimal("0").equals(BigDecimal.ZERO));
		Assert.assertTrue(new BigDecimal("1").equals(BigDecimal.ONE));
		Assert.assertTrue(new BigDecimal("123.1").equals(new BigDecimal("123.1")));
	}

	/**
	 * BigDecimal 반올림
	 * 
	 * @author noritersand
	 */
	@Test
	public void roundDecimalPoint() {
		BigDecimal floatNumber = new BigDecimal("12.536512304");
		Assert.assertEquals(new BigDecimal("12.54"), floatNumber.setScale(2, RoundingMode.HALF_UP));
		Assert.assertEquals(new BigDecimal("12.537"), floatNumber.setScale(3, RoundingMode.HALF_UP));
		Assert.assertEquals(new BigDecimal("12.5365"), floatNumber.setScale(4, RoundingMode.HALF_UP));
	}

	/**
	 * BigDecimal 올림
	 * 
	 * @author noritersand
	 */
	@Test
	public void ceilDecimalPoint() {
		BigDecimal floatNumber = new BigDecimal("12.536512304");
		BigDecimal roundedNumber = floatNumber.setScale(2, RoundingMode.CEILING);
		Assert.assertEquals(new BigDecimal("12.54"), roundedNumber);
		roundedNumber = floatNumber.setScale(3, RoundingMode.CEILING);
		Assert.assertEquals(new BigDecimal("12.537"), roundedNumber);
		roundedNumber = floatNumber.setScale(4, RoundingMode.CEILING);
		Assert.assertEquals(new BigDecimal("12.5366"), roundedNumber);
	}

	/**
	 * BigDecimal 내림(버림)
	 * 
	 * @author noritersand
	 */
	@Test
	public void floorDecimalPoint() {
		BigDecimal floatNumber = new BigDecimal("12.536512304");
		BigDecimal roundedNumber = floatNumber.setScale(2, RoundingMode.FLOOR);
		Assert.assertEquals(new BigDecimal("12.53"), roundedNumber);
		roundedNumber = floatNumber.setScale(3, RoundingMode.FLOOR);
		Assert.assertEquals(new BigDecimal("12.536"), roundedNumber);
		roundedNumber = floatNumber.setScale(4, RoundingMode.FLOOR);
		Assert.assertEquals(new BigDecimal("12.5365"), roundedNumber);
	}

	/**
	 * double은 소수점 계산에서 오차 발생함.
	 * 
	 * @author fixalot
	 */
	@Test
	public void calculateError() {
		double val1 = 1.5;
		double val2 = 0.3;
		BigDecimal bigVal1 = new BigDecimal("1.5");
		BigDecimal bigVal2 = new BigDecimal("0.3");

		Assert.assertEquals(0.44999999999999996D, val1 * val2, 0);
		Assert.assertEquals(new BigDecimal("0.45"), bigVal1.multiply(bigVal2));
	}
}
