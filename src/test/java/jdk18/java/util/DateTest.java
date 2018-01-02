package jdk18.java.util;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class DateTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

	@Test
	public void test() {
		Date now = new Date();
		logger.debug(String.valueOf(now));
	}
}
