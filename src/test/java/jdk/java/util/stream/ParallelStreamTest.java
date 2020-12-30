package jdk.java.util.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 병렬 Stream 테스트
 * 
 * required jdk8 or higher
 * 
 * - https://futurecreator.github.io/2018/08/26/java-8-streams/ - https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/
 * 
 * @since 2018-07-16
 * @author fixalot
 */
public class ParallelStreamTest {
	private static final Logger logger = LoggerFactory.getLogger(ParallelStreamTest.class);

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
