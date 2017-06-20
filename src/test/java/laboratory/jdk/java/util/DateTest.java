package laboratory.jdk.java.util;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DateTest.class);
	
	@Test
	public void test() {
		Date now = new Date();
		logger.debug(String.valueOf(now));
	}
}
