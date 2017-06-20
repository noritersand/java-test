package laboratory.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorTest {
	private static final Logger logger = LoggerFactory.getLogger(ErrorTest.class);
	
	@Test
	public void test() {
		try {
			throw new Error();
		} catch (Throwable e) {
			logger.debug("하이");
		}
	}
}
