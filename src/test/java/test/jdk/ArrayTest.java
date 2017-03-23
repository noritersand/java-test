package test.jdk;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayTest {
	private static final Logger log = LoggerFactory.getLogger(ArrayTest.class);

	@Test
	public void testForSomeoneFromHashcode() {
		// 여기에서 디비에 저장을 하면 0,0 1,1 2,2 이런식으로 저장되는데 0,0 0,1 0,2 이식으로 저장할라면 for문을 어떡해 돌려야되나요?
//		for (int i = 0; i < data.length; i++) {
//			for (int j = 0; j < data[i].length; j++) {
//				System.out.println(sb.toString());
//
//				pstmt = conn.prepareStatement(sb.toString(), 
//						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//				pstmt.setString(1, data[i][j]);
//
//				pstmt.setString(2, data[i][j]);
//
//				pstmt.setString(3, data[i][j]);
//				pstmt.executeUpdate();
//				System.out.println(i + "======" + j);
//			}
//		}
		int[][] data = new int[][] { { 0, 1, 2 }, { 0, 1, 2 } };
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				log.debug("data[" + i + "][" + j + "] = " + String.valueOf(data[i][j]));
			}
		}
	}

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
