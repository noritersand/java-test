package jdk.java.util;

import java.util.LinkedList;
import java.util.List;
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
			
			int num = random.nextInt(); // int의 최솟값 이상 최댓값 미만 사이의 랜덤값 추출
			logger.debug(String.valueOf(num));
			Assert.assertTrue(Integer.MIN_VALUE <= num && num < Integer.MAX_VALUE);
			
			num = random.nextInt(5); // 0 이상 5 미만 사이의 랜덤값 추출
			logger.debug(String.valueOf(num));
			Assert.assertTrue(0 <= num && num < 5);
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
	
	/**
	 * List의 요소를 난수로 꺼내기
	 * 
	 * @author fixalot
	 */
	@Test
	public void getRandomElement() {
		List<String> list = new LinkedList<>();
		list.add("하나");
		list.add("둘");
		list.add("셋");
		list.add("넷");
		list.add("다섯");
		list.add("여섯");
		list.add("일곱");
		list.add("여덟");
		list.add("아홉");
		list.add("열");
		for (int i = 0; i < 100; ++i) {
			int randomNumber = new Random().nextInt(list.size());
			String element = list.get(randomNumber);
			Assert.assertNotNull(element);
			Assert.assertFalse(element.isEmpty());
			Assert.assertTrue(element instanceof String);
			if (i == 99) {
				logger.debug(element);
			}
		}
	}
}
