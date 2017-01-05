package java.lang;
import org.junit.Assert;
import org.junit.Test;

public class NumberTest {
	@Test
	public void testAutoBoxing() {
		Assert.assertEquals(1, (long) new Long(1));
	}

	@Test
	public void testNumberRange() {
		byte byteValue = -128;
		byte byteValue2 = 127;
		short shortValue = -32768;
		short shortValue2 = 32767;
		int intValue = -2147483648;
		int intValue2 = 2147483647;
		long longValue = -9223372036854775808L;
		long longValue2 = 9223372036854775807L;

		Assert.assertEquals(-128, byteValue);
		Assert.assertEquals(127, byteValue2);
		Assert.assertEquals(-32768, shortValue);
		Assert.assertEquals(32767, shortValue2);
		Assert.assertEquals(-2147483648, intValue);
		Assert.assertEquals(2147483647, intValue2);
		Assert.assertEquals(-9223372036854775808L, longValue);
		Assert.assertEquals(9223372036854775807L, longValue2);
	}
}
