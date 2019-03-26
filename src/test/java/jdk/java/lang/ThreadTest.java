package jdk.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread 테스트
 * 
 * @since 2017-07-05
 * @author fixalot
 */
public class ThreadTest {
	private static final Logger logger = LoggerFactory.getLogger(ThreadTest.class);

	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				logger.debug("i'm main!");
			}
		};
		thread.start();
	}

	@Test
	public void getCurrentMethodName() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		// System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
		// return ste[ste.length - depth].getMethodName(); //Wrong, fails for depth = 0
//		return ste[ste.length - 1 - depth].getMethodName(); // Thank you Tom Tresansky

		logger.debug(ste[ste.length - 1].getMethodName());
		logger.debug(ste[ste.length - 2].getMethodName());
		logger.debug(ste[ste.length - 3].getMethodName());
		logger.debug(ste[ste.length - 4].getMethodName());
		logger.debug(ste[ste.length - 5].getMethodName());

//		logger.debug(ste[ste.length - 1 - depth].getMethodName());
//		logger.debug(ste[ste.length - 1 - depth].getMethodName());
	}
}
