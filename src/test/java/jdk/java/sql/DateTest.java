package jdk.java.sql;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date 테스트 유닛
 * 
 * @since 2019-01-11
 * @author fixalot
 */
public class DateTest {
	private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

	@Test
	public void shouldBeEqual() {
		long mills = System.currentTimeMillis();
		logger.debug("currentTimeMills: {}", mills);
		Date date = new Date(mills);
		Assert.assertEquals(mills, date.getTime());
	}
	
	@Test
	public void testJavaSqlDate() {
		Date date = new Date(1547168374396L);
		Assert.assertEquals("2019-01-11", date.toString());
	}
	
	@Test
	public void testJavaSqlTime() {
		Time time = new Time(1547168374396L);
		Assert.assertEquals("09:59:34", time.toString());
		
	}
	
	@Test
	public void testJavaSqlTimestamp() {
		Timestamp timestamp = new Timestamp(1547168374396L);
		Assert.assertEquals("2019-01-11 09:59:34.396", timestamp.toString());
	}
}
