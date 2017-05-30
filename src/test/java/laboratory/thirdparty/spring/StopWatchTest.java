package laboratory.thirdparty.spring;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

public class StopWatchTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(StopWatchTest.class);
	
	@Test
	public void test() {
		StopWatch watch = new StopWatch("IN SOVIET RUSSIA, MONITOR WATCHES YOU");
		
		watch.start("first");
		Assert.assertEquals("first", watch.currentTaskName());
		doSome();
		watch.stop();
		
		watch.start("second");
		doSome();
		watch.stop();
		
		watch.start("third");
		doSome();
		watch.stop();

		log.debug(watch.prettyPrint());
		
		Assert.assertEquals(3, watch.getTaskCount());
		Assert.assertEquals(3, watch.getTaskInfo().length);
		
		log.debug(watch.shortSummary());
		
		Assert.assertEquals("IN SOVIET RUSSIA, MONITOR WATCHES YOU", watch.getId());
		
		TaskInfo info = watch.getLastTaskInfo();
		Assert.assertEquals("third", info.getTaskName());
		log.debug(String.valueOf(info.getTimeMillis()));
		log.debug(String.valueOf(info.getTimeSeconds()));
	}
	
	private long doSome() {
		long sum = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
}
