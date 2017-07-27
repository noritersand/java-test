package laboratory.jdk;

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
		final Integer a = new Integer(123);
		Integer b = a;
		Assert.assertEquals(123, b.intValue());
		b = 456; // b = new Integer(456); String과 마찬가지로 새 객체가 할당된다.
		Assert.assertEquals(123, a.intValue());
		Assert.assertEquals(456, b.intValue());
	}
}
