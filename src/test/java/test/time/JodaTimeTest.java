package test.time;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JodaTimeTest {
	private static final Logger log = LoggerFactory.getLogger(JodaTimeTest.class);
	
	@Test
	public void testNow() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		String dt = DateTime.now().toString(formatter);
		log.debug("testNow: " + dt);
	}
	
	@Test
	public void testFromString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt = formatter.parseDateTime("2016-02-05");
		Assert.assertEquals("2016-02-05T00:00:00.000+09:00", dt.toString());
	}
	
	@Test
	public void testGetter() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime dt = formatter.parseDateTime("2016-02-15 13:40:30.123");

		// 시대(ERA)
		Assert.assertEquals(1, dt.getEra());
		// 세기
		Assert.assertEquals(20, dt.getCenturyOfEra());
		// 년
		Assert.assertEquals(2016, dt.getYear());
		Assert.assertEquals(16, dt.getYearOfCentury());
		Assert.assertEquals(2016, dt.getYearOfEra());
		Assert.assertEquals(2016, dt.getWeekyear());
		// 달
		Assert.assertEquals(2, dt.getMonthOfYear());
		// 주
		Assert.assertEquals(7, dt.getWeekOfWeekyear());
		// 지역
		Assert.assertEquals("Asia/Seoul", dt.getZone().toString());
		// 일
		Assert.assertEquals(46, dt.getDayOfYear());
		Assert.assertEquals(15, dt.getDayOfMonth());
		Assert.assertEquals(1, dt.getDayOfWeek());
		// 시간
		Assert.assertEquals(13, dt.getHourOfDay());
		// 분
		Assert.assertEquals(820, dt.getMinuteOfDay());
		Assert.assertEquals(40, dt.getMinuteOfHour());
		// 초
		Assert.assertEquals(49230, dt.getSecondOfDay());
		Assert.assertEquals(30, dt.getSecondOfMinute());
		// 밀리초
		Assert.assertEquals(123, dt.getMillisOfSecond());
		Assert.assertEquals(1455511230123L, dt.getMillis());
	}
	
	@Test
	public void testFromJavaUtilDate() {
		Date date = GregorianCalendar.getInstance().getTime();
		DateTime dt = new DateTime(date);
		log.debug("testFromJavaUtilDate: " + dt.toString());
	}
	
	/**
	 * 실패한 테스트
	 * 
	 * @author fixalot
	 */
	@Test
	public void testDateSplit() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime start = formatter.parseDateTime("2017-01-01");
		DateTime end = formatter.parseDateTime("2017-02-03");
		log.debug(start.toString());
		log.debug(end.toString());
	
//		Period p = new Period(start, end);
//		(p.getYears() * 365) 
//		p.getDays()
		
		Interval interval = new Interval(start.toDate().getTime(), end.toDate().getTime());
		Period period = interval.toPeriod();

		System.out.printf("%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n", period.getYears(), period.getMonths(),
				period.getDays(), period.getHours(), period.getMinutes(), period.getSeconds());
	}
	
	@Test
	public void testToFormatString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");
		DateTime dt = new DateTime();
		DateTime newDt = dt.withYear(2020).withMonthOfYear(2).withDayOfMonth(29).withHourOfDay(23)
				.withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(10);
		
		Assert.assertEquals("2020-02-29T23:59:59.010+09:00", newDt.toString());
		Assert.assertEquals("2020-02-29 23:59:59:010", newDt.toString(formatter));
	}
	
	@Test
	public void testEqual() {
//		Assert.assertEquals(new DateTime(), DateTime.now()); // 호출시점에 따라 몇 밀리초 차이로 같지 않을 수 있음
		Assert.assertEquals(new DateTime(2020, 2, 29, 23, 59, 59, 10), 
				new DateTime().withYear(2020).withMonthOfYear(2).withDayOfMonth(29).withHourOfDay(23)
						.withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(10));
	}
	
	@Test
	public void testFromFormatString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");
		Assert.assertEquals(new DateTime(2020, 2, 29, 23, 59, 59, 10), 
				formatter.parseDateTime("2020-02-29 23:59:59:010"));
	}
	
	@Test
	public void testPlusDay() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");
		DateTime dt = new DateTime(2020, 2, 29, 23, 59, 59, 10);
		DateTime newInstance = dt.plusDays(1);
		Assert.assertEquals("2020-03-01 23:59:59:010", newInstance.toString(formatter));
		newInstance = dt.plus(Period.days(1));
		Assert.assertEquals("2020-03-01 23:59:59:010", newInstance.toString(formatter));
		newInstance = dt.plus(new Duration(24L*60L*60L*1000L));
		Assert.assertEquals("2020-03-01 23:59:59:010", newInstance.toString(formatter));
	}
}
