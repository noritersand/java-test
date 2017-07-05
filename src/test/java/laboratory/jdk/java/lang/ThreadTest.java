package laboratory.jdk.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 2017-07-05
 * @author fixalot
 */
public class ThreadTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ThreadTest.class);
	
	public static void main(String[] args) {
		Thread thread = new Thread() {
			public void run() {
				logger.debug("i'm thread!");
			}
		};
		thread.start();
	}
	
	@Test
	public void createThread() {
		logger.debug("i'm createThread!"); // XXX 이 줄이 없으면 쓰레드 내의 logger 작동하지 않음. 이상하드아
		Thread thread = new Thread() {
			public void run() {
				logger.debug("i'm run!");
			}
		};
		thread.start();
	}
}
