package laboratory.test.java.math;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(BigDecimalTest.class);
	
	@Test
	public void testSum() {
		BigDecimal a = new BigDecimal("10");
		BigDecimal b = new BigDecimal("20");
		
		Assert.assertEquals(new BigDecimal("30"), a.add(b));
		Assert.assertEquals(new BigDecimal("10"), b.subtract(a));
		Assert.assertEquals(new BigDecimal("200"), a.multiply(b));
		Assert.assertEquals(new BigDecimal("2"), b.divide(a));
	}
}
