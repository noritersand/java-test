package jdk.statement;

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
public class ArrayTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArrayTest.class);

	@Test
	public void getString() {
		int[][] arr = new int[][] { { 1, 2, 6 }, { 3, 4 } };
		String[] str = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (str[i] == null) {
					str[i] = "";
				}
				if (j != 0) {
					str[i] += "x";
				}
				str[i] += String.valueOf(arr[i][j]);
			}
		}
		Assert.assertTrue(Arrays.equals(new String[] { "1x2x6", "3x4" }, str));
	}

	@Test
	public void getLength() {
		int[][] arr = new int[][] { { 1, 2, 6 }, { 3, 4 } };
		Assert.assertEquals(2, arr.length);
		Assert.assertEquals(3, arr[0].length);
		Assert.assertEquals(2, arr[1].length);
	}

	@Test
	public void join() {
		final String[] arr = { "a", "b", "c" };
		Assert.assertEquals("a,b,c", String.join(",", arr));
	}
}
