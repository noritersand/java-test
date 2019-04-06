package jdk.java.util.stream;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stream 클래스 테스트 유닛
 * 
 * required jdk8 or higher
 * 
 * - https://futurecreator.github.io/2018/08/26/java-8-streams/ - https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/
 * 
 * @since 2018-07-16
 * @author fixalot
 */
public class StreamTest {
	private static final Logger logger = LoggerFactory.getLogger(StreamTest.class);

	/**
	 * Stream.forEach() 테스트<br>
	 * forEach로 한 바퀴 돌면 스트림이 닫혔거나 작업이 끝난걸로 간주되어 다시 스트림을 사용할 수 없나보다.
	 * 
	 * @throws IOException
	 * @author fixalot
	 */
	@Test
	public void testForEach() throws IOException {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 3, 7 }));
		Stream<Integer> stream = list.stream();
		stream.forEach(System.out::println);
		stream.close(); // 생략 가능

		Stream<Integer> stream2 = list.stream();
		List<Integer> basket = new ArrayList<Integer>();
		stream2.forEach((k) -> {
			logger.debug("ele: {}", k.toString());
			basket.add(k);
		});
		stream2.close();
		Assert.assertArrayEquals(new Integer[] { 1, 3, 7 }, basket.toArray(new Integer[list.size()]));
	}

	@Test
	public void testParallelLoopOldWay() {
		List<String> list = new ArrayList<String>(Arrays.asList(new String[] { "01", "02", "03", "04", "05", "06", "07", "08" }));
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < list.size(); i++) {
			final String element = list.get(i);
			executor.submit(() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				logger.debug("testParallelLoopOldWay Starting:" + Thread.currentThread().getName() + ", element=" + element + ", ended at "
						+ LocalDateTime.now());
			});
		}
		executor.shutdown();
	}

	@Test
	public void testParallelStream() {
		List<String> list = new ArrayList<String>(Arrays.asList(new String[] { "하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟" }));
		list.parallelStream().forEach(element -> {
			logger.debug(
					"testParallelStream Starting:" + Thread.currentThread().getName() + ", element=" + element + ", " + LocalDateTime.now());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		});
	}
}
