package thirdparty.apache.commons;

import org.apache.commons.lang3.time.StopWatch;
import org.joda.time.DateTime;
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
public class StopWatchTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StopWatchTest.class);

	@Test
	public void test() {
		StopWatch watch = new StopWatch();
		doUselessThing();
		Assert.assertEquals(0, watch.getNanoTime()); // 시작하기 전에는 0

		watch.start(); // 시작
		Assert.assertTrue(watch.isStarted());

		doUselessThing();
		logger.debug("경과 시간: " + String.valueOf(watch.getNanoTime()));

		watch.suspend(); // 일시정지
		Assert.assertTrue(watch.isSuspended());

		watch.resume(); // 재시작
		Assert.assertTrue(watch.isStarted());

		watch.split(); // 임시 기록
		logger.debug("임시 기록 시간: " + String.valueOf(watch.getSplitNanoTime()));

		logger.debug("시작된 시간: " + String.valueOf(new DateTime(watch.getStartTime())));

		watch.stop(); // 중단
		watch.reset(); // 초기화

		Assert.assertTrue(watch.isStopped());
	}

	private long doUselessThing() {
		long sum = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
}
