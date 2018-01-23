package jdk.java.util;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2018-01-23
 * @author fixalot
 */
public class RandomTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RandomTest.class);

	@Test
	public void testNextInt() {
		for (int i = 0; i < 1000; ++i) {
			Random random = new Random();
			int num = random.nextInt(); // int의 최솟값 ~ 최댓값 사이에서 랜덤값 추출
			Assert.assertTrue(Integer.MIN_VALUE <= num && num <= Integer.MAX_VALUE);
			num = random.nextInt(1000); // 0부터 1000 사이에서 랜덤값 추출
			Assert.assertTrue(0 <= num && num <= 1000);
		}
	}
	
	@Test
	public void testInts() {
		Random random = new Random();
		IntStream a = random.ints();
		logger.debug(String.valueOf(a.count()));
		a = random.ints(100);
		logger.debug(String.valueOf(a.count()));
	}
}
