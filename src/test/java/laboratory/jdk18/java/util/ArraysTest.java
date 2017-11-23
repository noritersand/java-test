package laboratory.jdk18.java.util;

import java.util.Arrays;
import java.util.List;

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
	public void testToString() {
		String[] a = { "a", "b", "c" };
		logger.debug(Arrays.toString(a));
	}

	@Test
	public void shouldEqual() {
		String[] a = { "a", "b", "c" };
		String[] b = { "a", "b", "c" };
		Assert.assertTrue(Arrays.equals(a, b));
	}
	
	@Test
	public void testAsList() {
		List<Integer> list = Arrays.asList(new Integer[] { 6, 3, 1, 56, 99, 2, 41, 27, 54, 3 });
		List<Integer> list2 = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
		Assert.assertEquals(list, list2);
	}
}
