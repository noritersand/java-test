package laboratory.test.time;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
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

public class CalculateElapsedTimeTest {
	private static final Logger log = LoggerFactory.getLogger(CalculateElapsedTimeTest.class);

	@Test
	public void testByMilliseconds() {
		long start = new GregorianCalendar(2016, 2, 5).getTimeInMillis();
		long end = new GregorianCalendar(2017, 2, 5).getTimeInMillis();
		long elapsedDays = (end - start) / 1000 / 60 / 60 / 24;
		Assert.assertEquals(365, elapsedDays);
		
		start = new GregorianCalendar(2016, 2, 5, 21, 59, 59).getTimeInMillis();
		end = new GregorianCalendar(2016, 2, 5, 23, 59, 59).getTimeInMillis();
		elapsedDays = (end - start) / 1000;
		Assert.assertTrue(86400 > elapsedDays); // 2시간 전이라 86400초(하루) 보다 작다.
		
		start = new GregorianCalendar(2016, 2, 5).getTimeInMillis();
		end = new GregorianCalendar(2016, 2, 5, 23, 59, 59).getTimeInMillis();
		elapsedDays = (end - start) / 1000;
		Assert.assertTrue(86400 > elapsedDays); // 23시간 59분 59초 전
		
		start = new GregorianCalendar(2016, 2, 4).getTimeInMillis();
		end = new GregorianCalendar(2016, 2, 6).getTimeInMillis();
		elapsedDays = (end - start) / 1000;
		Assert.assertTrue(86400 < elapsedDays); // 이틀 전이라 86400초(하루) 보다 크다.
	}
	
	/**
	 * 일수만 계산하기
	 * 
	 * @author fixalot
	 */
	@Test
	public void testByJodaTimeJustDays() {
		DateTime start = new DateTime("2017-01-01");
		DateTime end = new DateTime("2017-05-03");
		Assert.assertEquals(122, Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
		end = new DateTime("2017-01-01");
		Assert.assertEquals(0, Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
		end = new DateTime("2017-01-02");
		Assert.assertEquals(1, Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
		end = new DateTime("2017-01-03");
		Assert.assertEquals(2, Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
		end = new DateTime("2017-01-05"); // 3박4일
		Assert.assertEquals(4, Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
	}
	
	/**
	 * Interval로 계산하는 방법
	 * 
	 * @author fixalot
	 */
	@Test
	public void testByJodaTimeWithInterval() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime start = formatter.parseDateTime("2017-01-01");
		DateTime end = formatter.parseDateTime("2017-01-03");
	
		Interval interval = new Interval(start.toDate().getTime(), end.toDate().getTime());
		org.joda.time.Period period = interval.toPeriod();

		log.debug(String.format("%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n", 
				period.getYears(), period.getMonths(), period.getDays(), 
				period.getHours(), period.getMinutes(), period.getSeconds()));
	}
	
	@Test
	public void testByJodaTimeWithInterval2() {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime now = new DateTime();
		DateTime dt1 = now.withYear(2020).withMonthOfYear(2).withDayOfMonth(29);
		DateTime dt2 = now.withYear(2020).withMonthOfYear(3).withDayOfMonth(01);
		Assert.assertEquals("2020-02-29", dt1.toString(format));
		Interval interval = new Interval(dt1, dt2);
		log.debug(interval.getStart().toString(format));
	}
	
	/**
	 * Interval을 스킵하고 Period를 직접 사용하는 방법
	 * 
	 * @author fixalot
	 */
	@Test
	public void testByJodaTimeWithPeriodFormatter() {
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
	@Test
	public void testByJodaTimeWithPeriod() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime start = formatter.parseDateTime("2017-01-01");
		DateTime end = formatter.parseDateTime("2017-01-03");
		
		Period period = new Period(start, end);
		
		log.debug(PeriodFormat.getDefault().print(period));
	}
	
	@Test
	public void testByJavaTime() {
		LocalDate targetDay = LocalDate.of(2017, Month.DECEMBER, 31);
//		LocalDate today = LocalDate.now();
		LocalDate today = LocalDate.of(2017, Month.JANUARY, 24);
		
		java.time.Period p = java.time.Period.between(today, targetDay);
		long p2 = ChronoUnit.DAYS.between(today, targetDay);
		Assert.assertEquals("0 years, 11 months, 7 days later. (341 days total)", 
				(p.getYears() + " years, " + p.getMonths() + " months, " + p.getDays() + " days later. (" + p2 + " days total)"));
	}
	
	@Test
	public void testByJavaTime2() {
//		LocalDate today = LocalDate.now();
		LocalDate today = LocalDate.of(2017, Month.JANUARY, 24);
		LocalDate birthday = LocalDate.of(1984, Month.JULY, 9);
		
		java.time.Period p = java.time.Period.between(birthday, today);
		long p2 = ChronoUnit.DAYS.between(birthday, today);
		Assert.assertEquals("You are 32 years, 6 months, and 15 days old. (11887 days total)", 
				"You are " + p.getYears() + " years, " + p.getMonths() + " months, and " + p.getDays() + " days old. (" + p2 + " days total)");
	}
}
