package jdk.statement;

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
public class WrapperClassTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WrapperClassTest.class);

	@Test
	public void copy() {
		final Integer a = Integer.valueOf(123);
		Integer b = a;
		Assert.assertEquals(123, b.intValue());
		b = 456; // b = new Integer(456); String과 마찬가지로 새 객체가 할당된다.
		Assert.assertEquals(123, a.intValue());
		Assert.assertEquals(456, b.intValue());
	}
	
	@Test
	public void testEquals() {
		Long a = Long.valueOf(12345);
		Long b = Long.valueOf(12345);
		Assert.assertFalse(a == b); // wrapper 타입은 동등 연산자에서 객체 아이디를 비교하지 값을 비교하지 않음.
		Assert.assertTrue(a.equals(b)); // correct
	}
}
