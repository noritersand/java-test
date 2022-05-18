package jdk.java.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDK BigInteger 테스트
 * 
 * @since 2019-12-16
 * @author noritersand
 */
public class BigIntegerTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BigIntegerTest.class);

	@Test
	public void instantiate() {
		assertEquals(BigInteger.valueOf(123), new BigInteger("123"));
//		assertEquals(BigInteger.valueOf(123), new BigInteger("123.456")); // java.lang.NumberFormatException: For input string: "123.456"
	}
	
	@Test
	public void fromBigDecimal() {
		// BigInteger로 변환시 소수점 이하는 버린다.
		assertEquals(BigInteger.TWO, new BigDecimal("2.123").toBigInteger());
		assertEquals(BigInteger.TWO, new BigDecimal("2.723").toBigInteger());
	}
	
	@Test
	public void arithmeticOperation() {
		BigInteger ten = BigInteger.TEN;
		BigInteger two = BigInteger.TWO;
		assertEquals(BigInteger.valueOf(12), ten.add(two));
		assertEquals(BigInteger.valueOf(8), ten.subtract(two));
		assertEquals(BigInteger.valueOf(20), ten.multiply(two));
		assertEquals(BigInteger.valueOf(5), ten.divide(two));
	}
	
	@Test
	public void logicalOperation() {
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		assertEquals("-1", zero.not().toString());
		assertEquals(BigInteger.ZERO, zero.and(one));
		assertEquals(BigInteger.ZERO, zero.andNot(one));
		assertEquals(BigInteger.ONE, zero.or(one));
		assertEquals(BigInteger.ONE, zero.xor(one));
	}
}
