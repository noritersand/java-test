package laboratory.thirdparty.spring;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

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
		StopWatch watch = new StopWatch("IN SOVIET RUSSIA, MONITOR WATCHES YOU");

		watch.start("first");
		Assert.assertEquals("first", watch.currentTaskName());
		doUselessThing();
		watch.stop();

		watch.start("second");
		doUselessThing();
		watch.stop();

		watch.start("third");
		doUselessThing();
		watch.stop();

		logger.debug(watch.prettyPrint());

		Assert.assertEquals(3, watch.getTaskCount());
		Assert.assertEquals(3, watch.getTaskInfo().length);

		logger.debug(watch.shortSummary());

		Assert.assertEquals("IN SOVIET RUSSIA, MONITOR WATCHES YOU", watch.getId());

		TaskInfo info = watch.getLastTaskInfo();
		Assert.assertEquals("third", info.getTaskName());
		logger.debug(String.valueOf(info.getTimeMillis()));
		logger.debug(String.valueOf(info.getTimeSeconds()));
	}

	private long doUselessThing() {
		long sum = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
}
