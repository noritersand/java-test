package jdk.java.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java.util.Date 테스트 유닛
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class DateTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

	@Test
	public void shouldBeEquals() {
		long mills = System.currentTimeMillis();
		logger.debug("currentTimeMills: {}", mills);
		Date now = new Date(mills);
		Assert.assertEquals(mills, now.getTime());
	}
	
	@Test
	public void testJavaUtilDate() {
		Date now = new Date();
		logger.debug("{}", now);
		
		now = new Date(1547168374396L);
		Assert.assertEquals("Fri Jan 11 09:59:34 KST 2019", now.toString());
	}
}