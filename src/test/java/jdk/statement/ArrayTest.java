package jdk.statement;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 배열 테스트 슈트
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
		assertTrue(Arrays.equals(new String[] { "1x2x6", "3x4" }, str));
	}
	@Test
	public void getLength() {
		int[][] arr = new int[][] { { 1, 2, 6 }, { 3, 4 } };
		assertEquals(2, arr.length);
		assertEquals(3, arr[0].length);
		assertEquals(2, arr[1].length);
	}
	@Test
	public void join() {
		final String[] arr = { "a", "b", "c" };
		assertEquals("a,b,c", String.join(",", arr));
	}

	@Test
	public void testFindFirstMatchUsingForStatement() {
		final String[] arr = { "/findme", "/beginning", "/start" };
        final String URL = "/beginning/list/hello";
		for (String e : arr) {
			if (URL.indexOf(e) == 0) {
				logger.debug("URL: {}", URL);
				logger.debug("e: {}", e);
			}
		}
	}
}
