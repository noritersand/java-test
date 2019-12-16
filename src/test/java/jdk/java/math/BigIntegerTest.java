package jdk.java.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDK BigInteger 테스트 유닛
 * 
 * @since 2019-12-16
 * @author noritersand
 */
public class BigIntegerTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BigIntegerTest.class);

	@Test
	public void instantiate() {
		Assert.assertEquals(BigInteger.valueOf(123), new BigInteger("123"));
//		Assert.assertEquals(BigInteger.valueOf(123), new BigInteger("123.456")); // java.lang.NumberFormatException: For input string: "123.456"
	}
	
	@Test
	public void fromBigDecimal() {
		// BigInteger로 변환시 소수점 이하는 버린다.
		Assert.assertEquals(BigInteger.TWO, new BigDecimal("2.123").toBigInteger());
		Assert.assertEquals(BigInteger.TWO, new BigDecimal("2.723").toBigInteger());
	}
	
	@Test
	public void arithmeticOperation() {
		BigInteger ten = BigInteger.TEN;
		BigInteger two = BigInteger.TWO;
		Assert.assertEquals(BigInteger.valueOf(12), ten.add(two));
		Assert.assertEquals(BigInteger.valueOf(8), ten.subtract(two));
		Assert.assertEquals(BigInteger.valueOf(20), ten.multiply(two));
		Assert.assertEquals(BigInteger.valueOf(5), ten.divide(two));
	}
	
	@Test
	public void logicalOperation() {
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		Assert.assertEquals("-1", zero.not().toString());
		Assert.assertEquals(BigInteger.ZERO, zero.and(one));
		Assert.assertEquals(BigInteger.ZERO, zero.andNot(one));
		Assert.assertEquals(BigInteger.ONE, zero.or(one));
		Assert.assertEquals(BigInteger.ONE, zero.xor(one));
	}
}
