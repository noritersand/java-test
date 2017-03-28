package laboratory.test.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ThreadTest.class);
	
	@Test
	public void testIWantCurrentMethodName() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		// System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
		// return ste[ste.length - depth].getMethodName(); //Wrong, fails for depth = 0
//		return ste[ste.length - 1 - depth].getMethodName(); // Thank you Tom Tresansky
		
		log.debug(ste[ste.length - 1].getMethodName());
		log.debug(ste[ste.length - 2].getMethodName());
		log.debug(ste[ste.length - 3].getMethodName());
		log.debug(ste[ste.length - 4].getMethodName());
		log.debug(ste[ste.length - 5].getMethodName());
		
//		log.debug(ste[ste.length - 1 - depth].getMethodName());
//		log.debug(ste[ste.length - 1 - depth].getMethodName());
	}
}
