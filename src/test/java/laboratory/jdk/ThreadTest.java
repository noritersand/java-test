package laboratory.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {
	private static final Logger logger = LoggerFactory.getLogger(ThreadTest.class);
	
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
