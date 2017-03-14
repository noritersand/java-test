package test.java.util;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTest {
	@Test
	public void testToString() {
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
	public void testLength() {
		int[][] arr = new int[][] { { 1, 2, 6 }, { 3, 4 } };
		Assert.assertEquals(2, arr.length);
		Assert.assertEquals(3, arr[0].length);
		Assert.assertEquals(2, arr[1].length);
	}
	
	
}
