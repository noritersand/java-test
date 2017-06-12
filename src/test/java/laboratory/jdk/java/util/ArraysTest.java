package laboratory.jdk.java.util;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArraysTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ArraysTest.class);
	
	@Test
	public void getString() {
		String[] a = { "a", "b", "c" };
		log.debug(Arrays.toString(a));
	}
	
	@Test
	public void shouldEquals() {
		String[] a = { "a", "b", "c" };
		String[] b = { "a", "b", "c" };
		Assert.assertTrue(Arrays.equals(a, b));
	}
}
