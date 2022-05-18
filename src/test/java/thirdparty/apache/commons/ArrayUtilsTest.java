package thirdparty.apache.commons;

import org.apache.commons.lang3.ArrayUtils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class ArrayUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArrayUtilsTest.class);

	@Test
	public void testSubarray() {
		final int[] arr = { 1, 2, 3, 4, 5 };
		assertArrayEquals(new int[] { 1, 2 }, ArrayUtils.subarray(arr, 0, 2));
		assertArrayEquals(new int[] { 2 }, ArrayUtils.subarray(arr, 1, 2));
		assertArrayEquals(new int[] { 1, 2, 3 }, ArrayUtils.subarray(arr, 0, 3));
		assertArrayEquals(new int[] { 3, 4 }, ArrayUtils.subarray(arr, 2, 4));
	}
}
