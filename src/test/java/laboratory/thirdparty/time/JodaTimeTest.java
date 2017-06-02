package laboratory.thirdparty.time;

import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JodaTimeTest {
	private static final Logger log = LoggerFactory.getLogger(JodaTimeTest.class);

//	//@Test
//	public void testDateValidate() {
//		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//	}

	//@Test
	public void testNow() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		String dt = DateTime.now().toString(formatter);
		log.debug("testNow: " + dt);
	}

	//@Test
	public void testFromString() {
		DateTime dt = new DateTime("2017-04-18T16:41:34.219+09:00");
		log.debug(dt.toString());
		DateTime dt2 = new DateTime(2017, 4, 18, 16, 41, 34, 219);
		Assert.assertEquals(dt, dt2);
	}
	
	//@Test
	public void testFromFormatString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime dt = formatter.parseDateTime("2016-02-05 00:00:00.000");
		Assert.assertEquals("2016-02-05T00:00:00.000+09:00", dt.toString());

		try {
			formatter.parseDateTime("20160205");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		DateTime dt2 = new DateTime("2015-01-01");
		Assert.assertEquals("2015-01-01T00:00:00.000+09:00", String.valueOf(dt2));
	}
	
	//@Test
	public void testToFormatString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");
		DateTime dt = new DateTime();
		DateTime newDt = dt.withYear(2020).withMonthOfYear(2).withDayOfMonth(29).withHourOfDay(23).withMinuteOfHour(59)
				.withSecondOfMinute(59).withMillisOfSecond(10);

		Assert.assertEquals("2020-02-29T23:59:59.010+09:00", newDt.toString());
		Assert.assertEquals("2020-02-29 23:59:59:010", newDt.toString(formatter));
	}
	
	//@Test
	public void testFromJavaUtilDate() {
		Date date = GregorianCalendar.getInstance().getTime();
		DateTime dt = new DateTime(date);
		log.debug("testFromJavaUtilDate: " + dt.toString());
	}

	//@Test
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
	
	/**
	 * 실패한 테스트
	 * 
	 * @author fixalot
	 */
	//@Test
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

	//@Test
	public void testEqual() {
//		Assert.assertEquals(new DateTime(), DateTime.now()); // 호출시점에 따라 몇 밀리초 차이로 같지 않을 수 있음
		Assert.assertEquals(
				new DateTime(2020, 2, 29, 23, 59, 59, 10), 
				new DateTime().withYear(2020).withMonthOfYear(2).withDayOfMonth(29).withHourOfDay(23)
						.withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(10));
	}

	//@Test
	public void testPlusDay() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");
		DateTime dt = new DateTime(2020, 2, 29, 23, 59, 59, 10);
		DateTime newInstance = dt.plusDays(1);
		Assert.assertEquals("2020-03-01 23:59:59:010", newInstance.toString(formatter));
		newInstance = dt.plus(Period.days(1));
		Assert.assertEquals("2020-03-01 23:59:59:010", newInstance.toString(formatter));
		newInstance = dt.plus(new Duration(24L * 60L * 60L * 1000L));
		Assert.assertEquals("2020-03-01 23:59:59:010", newInstance.toString(formatter));
	}

	//@Test
	public void testCalculateMonth() {
		DateTime dt = new DateTime(2017, 3, 31, 00, 00, 00);
		DateTime a = dt.minusMonths(1);
		Assert.assertEquals(new DateTime(2017, 2, 28, 00, 00, 00).toString(), a.toString());
		DateTime b = dt.plusMonths(1);
		Assert.assertEquals(new DateTime(2017, 4, 30, 00, 00, 00).toString(), b.toString());
	}
	
	/**
	 * 일수만 계산하기
	 * 
	 * @author fixalot
	 */
	//@Test
	public void testCalculatePeriodJustDays() {
		DateTime start = new DateTime("2017-01-01");
		DateTime end = new DateTime("2017-05-03");
		Assert.assertEquals(122, Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
		end = new DateTime("2017-01-01");
		Assert.assertEquals(0, Days.daysBetween(start, end).getDays());
		end = new DateTime("2017-01-02");
		Assert.assertEquals(1, Days.daysBetween(start, end).getDays());
		end = new DateTime("2017-01-03");
		Assert.assertEquals(2, Days.daysBetween(start, end).getDays());
		end = new DateTime("2017-01-05"); // 3박4일
		Assert.assertEquals(4, Days.daysBetween(start, end).getDays());
	}
	
	/**
	 * Interval로 계산하는 방법
	 * 
	 * @author fixalot
	 */
	//@Test
	public void testCalculatePeriodWithInterval() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime start = formatter.parseDateTime("2017-01-01");
		DateTime end = formatter.parseDateTime("2017-01-03");
	
		Interval interval = new Interval(start.toDate().getTime(), end.toDate().getTime());
		org.joda.time.Period period = interval.toPeriod();

		log.debug(String.format("%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n", 
				period.getYears(), period.getMonths(), period.getDays(), 
				period.getHours(), period.getMinutes(), period.getSeconds()));
	}
	
	//@Test
	public void testCalculatePeriodWithInterval2() {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime now = new DateTime();
		DateTime dt1 = now.withYear(2020).withMonthOfYear(2).withDayOfMonth(29);
		DateTime dt2 = now.withYear(2020).withMonthOfYear(3).withDayOfMonth(01);
		Assert.assertEquals("2020-02-29", dt1.toString(format));
		Interval interval = new Interval(dt1, dt2);
		Assert.assertEquals("2020-02-29", interval.getStart().toString(format));
	}
	
	/**
	 * Interval을 스킵하고 Period를 직접 사용하는 방법
	 * 
	 * @author fixalot
	 */
	//@Test
	public void testCalculatePeriodWithPeriodFormatter() {
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);

		// period of 1 year and 7 days
		Period period = new Period(start, end);
		
		PeriodFormatter formatter = new PeriodFormatterBuilder()
			    .appendYears().appendSuffix(" years ago\n")
			    .appendMonths().appendSuffix(" months ago\n")
			    .appendWeeks().appendSuffix(" weeks ago\n")
			    .appendDays().appendSuffix(" days ago\n")
			    .appendHours().appendSuffix(" hours ago\n")
			    .appendMinutes().appendSuffix(" minutes ago\n")
			    .appendSeconds().appendSuffix(" seconds ago\n")
			    .printZeroNever()
			    .toFormatter();
		
		log.debug(period.toString(formatter));
	}
	
	/**
	 * 가장 간단한 방법이지만 문자열로만 사용할 수 있는 방법
	 * 
	 * @author fixalot
	 */
	//@Test
	public void testCalculatePeriodWithPeriod() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime start = formatter.parseDateTime("2017-01-01");
		DateTime end = formatter.parseDateTime("2017-01-03");
		Period period = new Period(start, end);
		Assert.assertEquals("2 days", PeriodFormat.getDefault().print(period));
	}

	/**
	 * 날짜 비교
	 * 
	 * @author fixalot
	 */
	//@Test
	public void testCompare() {
		DateTime sometime = new DateTime("2017-04-18T01:00:00.000+09:00");
		DateTime oneMinuteLater = new DateTime("2017-04-18T01:01:00.000+09:00");
		
		Assert.assertTrue(sometime.isBefore(oneMinuteLater));
		Assert.assertTrue(oneMinuteLater.isAfter(sometime));
		
		Assert.assertTrue(sometime.getMillis() < oneMinuteLater.getMillis());
		Assert.assertEquals(1492444800000L, sometime.getMillis());
		Assert.assertEquals(1492444860000L, oneMinuteLater.getMillis());
		Assert.assertEquals(60000, oneMinuteLater.getMillis() - sometime.getMillis());
		Assert.assertEquals(1, (oneMinuteLater.getMillis() - sometime.getMillis()) / 1000 / 60); // 1부운 차이
	}
	
	/**
	 * a와 b 사이에 c가 있는지 테스트
	 * 
	 * @author fixalot
	 */
	//@Test
	public void testIsBetween() {
		DateTime a = new DateTime("2017-01-01");
		DateTime b = new DateTime("2017-01-03");
		
		DateTime c = new DateTime("2017-01-02");
		
		Assert.assertEquals(-1, a.compareTo(c)); // -1: a는 c보다 이전
		Assert.assertEquals(0, c.compareTo(c)); // 0: c는 d와 같음
		Assert.assertEquals(1, b.compareTo(c)); // 1: b는 c보다 이후
	}
	
//	@Test
//	public void test1() {
//		DateTimeZone zone = DateTimeZone.forID("Europe/London");
//		Chronology coptic = CopticChronology.getInstance(zone);
//
//		DateTime c = new DateTime("2017-01-02");
//		
//		// current time with coptic chronology
//		DateTime dt = new DateTime(coptic);
//		log.debug(String.valueOf(dt));
//		log.debug(String.valueOf(c));
//	}
	
	/**
	 * 같은 시각인지 비교
	 * 
	 * @author fixalot
	 */
	@Test
	public void testEquality() {
		// 기본적으론 비슷하지만
		DateTime c = new DateTime("2017-01-02");
		DateTime d = new DateTime("2017-01-02");
		Assert.assertTrue(c.isEqual(d));
		Assert.assertTrue(c.equals(d));
		
		c = new DateTime("2017-01-02T01:00:00", DateTimeZone.UTC); // GMT/UTC
		d = new DateTime("2017-01-02T10:00:00", DateTimeZone.forID("Asia/Seoul")); // GMT/UTC보다 9시간 빠름
		
		// isEqual은 밀리초(GMT/UTC 기준)가 같은지 비교한다.
		log.debug(String.valueOf(c.getMillis()));
		log.debug(String.valueOf(d.getMillis()));
		Assert.assertTrue(c.isEqual(d)); // 영국의 01시는 한국의 10시와 같다는 판단
		
		// equals는 timezone과 chronology을 적용한 시각이 같은지를 비교한다.
		Assert.assertEquals("2017-01-02T01:00:00.000Z", c.toString());
		Assert.assertEquals("2017-01-02T10:00:00.000+09:00", d.toString());
		Assert.assertFalse(c.equals(d)); // 01시와 10시를 단순 비교
	}
}
