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
 * @author fixalot
 */
public class BigDecimalTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BigDecimalTest.class);

	@Test
	public void getConst() {
		Assert.assertEquals(new BigDecimal(0), BigDecimal.ZERO);
		Assert.assertEquals(new BigDecimal(1), BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(10), BigDecimal.TEN);
	}

	@Test
	public void useOperator() {
		BigDecimal a = new BigDecimal("10");
		BigDecimal b = new BigDecimal("20");

		Assert.assertEquals(new BigDecimal("30"), a.add(b));
		Assert.assertEquals(new BigDecimal("10"), b.subtract(a));
		Assert.assertEquals(new BigDecimal("200"), a.multiply(b));
		Assert.assertEquals(new BigDecimal("2"), b.divide(a));
	}

	@Test
	public void testCompareTo() {
		Assert.assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimal.ZERO)); // a.compareTo(b)에서 0이면 a와 b가 같음
		Assert.assertEquals(-1, BigDecimal.ZERO.compareTo(BigDecimal.ONE)); // a.compareTo(b)에서 -1이면 a가 b보다 작음
		Assert.assertEquals(1, BigDecimal.ZERO.compareTo(new BigDecimal(-1))); // a.compareTo(b)에서 1이면 a가 b보다 큼
	}
	
	/**
	 * BigDecimal 반올림
	 * 
	 * @author fixalot
	 */
	@Test
	public void roundDecimalPoint() {
		BigDecimal floatNumber = new BigDecimal("12.536512304");
		BigDecimal roundedNumber = floatNumber.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(new BigDecimal("12.54"), roundedNumber);
		roundedNumber = floatNumber.setScale(3, RoundingMode.HALF_UP);
		Assert.assertEquals(new BigDecimal("12.537"), roundedNumber);
		roundedNumber = floatNumber.setScale(4, RoundingMode.HALF_UP);
		Assert.assertEquals(new BigDecimal("12.5365"), roundedNumber);
	}
	
	/**
	 * BigDecimal 올림
	 * 
	 * @author fixalot
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
	 * @author fixalot
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
}
