package laboratory.jdk.java.math;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(BigDecimalTest.class);
	
	@Test
	public void testConstants() {
		Assert.assertEquals(new BigDecimal(0), BigDecimal.ZERO);
		Assert.assertEquals(new BigDecimal(1), BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(10), BigDecimal.TEN);
	}
	
	@Test
	public void testOperator() {
		BigDecimal a = new BigDecimal("10");
		BigDecimal b = new BigDecimal("20");
		
		Assert.assertEquals(new BigDecimal("30"), a.add(b));
		Assert.assertEquals(new BigDecimal("10"), b.subtract(a));
		Assert.assertEquals(new BigDecimal("200"), a.multiply(b));
		Assert.assertEquals(new BigDecimal("2"), b.divide(a));
	}
	
	@Test
	public void testCompare() {
		BigDecimal a = BigDecimal.ZERO;
		Assert.assertEquals(0, a.compareTo(BigDecimal.ZERO)); //  a.compareTo(b)에서 0이면 a와 b가 같음
		Assert.assertEquals(-1, a.compareTo(BigDecimal.ONE)); // a.compareTo(b)에서 -1이면 a가 b보다 작음
		Assert.assertEquals(1, a.compareTo(new BigDecimal(-1))); // a.compareTo(b)에서 1이면 a가 b보다 큼
	}
}
