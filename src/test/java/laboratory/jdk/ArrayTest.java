package laboratory.jdk;

import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArrayTest.class);

	@Test
	public void reverse() {
//		int[] arr = new int[] { 1, 2, 6, 3, 4 };
		Integer[] arr = new Integer[] { 1, 2, 6, 3, 4 };
		CollectionUtils.reverseArray(arr);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 6, 2, 1 }, arr);
	}
	
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

		System.out.println(Arrays.toString(str));
	}

	@Test
	public void getLength() {
		int[][] arr = new int[][] { { 1, 2, 6 }, { 3, 4 } };
		Assert.assertEquals(2, arr.length);
		Assert.assertEquals(3, arr[0].length);
		Assert.assertEquals(2, arr[1].length);
	}

}
