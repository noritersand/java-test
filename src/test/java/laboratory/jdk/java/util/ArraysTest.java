package laboratory.jdk.java.util;

import java.util.Arrays;

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
public class ArraysTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArraysTest.class);

	@Test
	public void getString() {
		String[] a = { "a", "b", "c" };
		logger.debug(Arrays.toString(a));
	}

	@Test
	public void shouldEqual() {
		String[] a = { "a", "b", "c" };
		String[] b = { "a", "b", "c" };
		Assert.assertTrue(Arrays.equals(a, b));
	}
}
