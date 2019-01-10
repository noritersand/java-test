package jdk.java.util;

import java.util.Date;

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
public class DateTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

	@Test
	public void testJavaUtilDate() {
		Date now = new Date();
		logger.debug(String.valueOf(now));
	}
	
	@Test
	public void testJavaSqlDate() {
		long mills = System.currentTimeMillis();
		logger.debug("currentTimeMills: {}", mills);
		
		java.sql.Date now = new java.sql.Date(mills);
		Assert.assertEquals(mills, now.getTime());
		logger.debug(String.valueOf(now));
	}
}
