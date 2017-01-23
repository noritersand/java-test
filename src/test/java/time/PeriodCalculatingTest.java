package time;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeriodCalculatingTest {
	private static final Logger log = LoggerFactory.getLogger(PeriodCalculatingTest.class);
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void test2() {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime now = new DateTime();
		DateTime dt1 = now.withYear(2020).withMonthOfYear(2).withDayOfMonth(29);
		DateTime dt2 = now.withYear(2020).withMonthOfYear(3).withDayOfMonth(01);
		Assert.assertEquals("2020-02-29", dt1.toString(format));
		Interval interval = new Interval(dt1, dt2);
		log.debug(interval.getStart().toString(format));
	}
}
