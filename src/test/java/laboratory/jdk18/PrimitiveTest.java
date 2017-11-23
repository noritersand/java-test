package laboratory.jdk18;

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
public class PrimitiveTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PrimitiveTest.class);

	@Test
	@SuppressWarnings("unused")
	public void useEscapeCharacter() {
		Byte bt = 123;
		byte bt2 = 123;
		Short st = 0;
		short st2 = 0;
		Integer it = 12345;
		int it2 = 12345;
		Long lg = 1234L;
		long lg2 = 1234l;
		Float flt = 1234F;
		float flt2 = 1234f;
		Double db = 1234D;
		double db2 = 1234d;
	}

	@Test
	public void getMinMaxRange() {
		Assert.assertEquals(-128, Byte.MIN_VALUE);
		Assert.assertEquals(127, Byte.MAX_VALUE);
		Assert.assertEquals(-32768, Short.MIN_VALUE);
		Assert.assertEquals(32767, Short.MAX_VALUE);
		Assert.assertEquals(-2147483648, Integer.MIN_VALUE);
		Assert.assertEquals(2147483647, Integer.MAX_VALUE);
		Assert.assertEquals(-9223372036854775808L, Long.MIN_VALUE);
		Assert.assertEquals(9223372036854775807L, Long.MAX_VALUE);
		Assert.assertEquals("1.4E-45", String.valueOf(Float.MIN_VALUE));
		Assert.assertEquals("3.4028235E38", String.valueOf(Float.MAX_VALUE));
		Assert.assertEquals("4.9E-324", String.valueOf(Double.MIN_VALUE));
		Assert.assertEquals("1.7976931348623157E308", String.valueOf(Double.MAX_VALUE));
	}

	@Test
	public void autoBoxing() {
		Assert.assertEquals(1, (long) new Long(1));
	}
}
