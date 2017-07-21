package laboratory.thirdparty.apache.commons;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArrayUtilsTest.class);

	@Test
	public void subarray() {
		final int[] arr = { 1, 2, 3, 4, 5 };
		Assert.assertArrayEquals(new int[] { 1, 2 }, ArrayUtils.subarray(arr, 0, 2));
		Assert.assertArrayEquals(new int[] { 2 }, ArrayUtils.subarray(arr, 1, 2));
		Assert.assertArrayEquals(new int[] { 1, 2, 3 }, ArrayUtils.subarray(arr, 0, 3));
		Assert.assertArrayEquals(new int[] { 3, 4 }, ArrayUtils.subarray(arr, 2, 4));
	}
}
