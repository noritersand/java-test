package jdk.java.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

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
public class JavaTimeTest {
	private static final Logger logger = LoggerFactory.getLogger(JavaTimeTest.class);

	@Test
	public void create() {
		logger.debug("{}", Instant.now());
		logger.debug("{}", LocalDate.now()); // yyyy-MM-dd
		logger.debug("{}", LocalTime.now()); // HH:mm:ss.SSS

		Assert.assertEquals("1970-01-01T00:00:00Z", Instant.ofEpochMilli(0).toString());
		Assert.assertEquals("2017-09-19T06:10:46.820Z", Instant.ofEpochMilli(1505801446820L).toString());
		Assert.assertEquals("2009-02-13T23:20:23Z", Instant.ofEpochSecond(1234567223L).toString());
		Assert.assertTrue(Instant.ofEpochMilli(915152400123L).equals(Instant.parse("1999-01-01T01:00:00.123Z"))); // 표준 포맷으로 생성하기

		// create instance from ISO date time string
		Assert.assertEquals("2017-04-18T01:24:48.842Z", Instant.parse("2017-04-18T01:24:48.842Z").toString());
	}

	@Test
	public void setCustomTime() {
		LocalDateTime now = LocalDateTime.now();
		now = now.withYear(2019).withMonth(1).withDayOfMonth(31);
		now = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
		Assert.assertEquals(LocalDateTime.parse("2019-01-31T00:00:00.000"), now);
	}

	@Test
	public void getLocalDate() {
		LocalDate a = LocalDate.now();
		logger.debug("{}", a); // yyyy-MM-dd
		Assert.assertEquals("2017-12-31", LocalDate.of(2017, Month.DECEMBER, 31).toString());
		Assert.assertEquals("2017-04-10T23:49", LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString());
	}

	@Test
	public void getMonthDay() {
		MonthDay a = MonthDay.now();
		logger.debug("{}", a); // --MM-dd
	}

	@Test
	public void getYearMonth() {
		YearMonth a = YearMonth.now();
		logger.debug("{}", a); // yyyy-MM
	}

	@Test
	public void getOffsetDateTime() {
		Assert.assertEquals("2017-12-31T23:59:59.999999999Z",
				OffsetDateTime.of(LocalDate.of(2017, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC).toString());
		Assert.assertEquals("2017-01-14T10:20:30Z", OffsetDateTime.of(2017, 1, 14, 10, 20, 30, 0, ZoneOffset.UTC).toString());
	}

	@Test
	public void getOffsetTime() {
		Assert.assertEquals("22:58+18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MAX).toString());
		Assert.assertEquals("22:58-18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MIN).toString());
		Assert.assertEquals("22:58Z", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.UTC).toString());
	}

	@Test
	public void splitDate() {
		LocalDate start = new GregorianCalendar(2016, 2, 5).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = new GregorianCalendar(2016, 2, 11).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		long periodDays = ChronoUnit.DAYS.between(start, end);
		List<LocalDate> list = new LinkedList<>();
		for (int i = 0; i < periodDays; i++) {
			list.add(start.plusDays(i));
		}
		Assert.assertEquals("[2016-03-05, 2016-03-06, 2016-03-07, 2016-03-08, 2016-03-09, 2016-03-10]", list.toString());
	}

	/**
	 * java.util.Date -> String
	 * 
	 * @author fixalot
	 */
	@Test
	public void parseToStringFromJavaUtilDate() {
		Calendar input = new GregorianCalendar(2016, 2, 5);
		Date date = input.getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Assert.assertEquals("2016-03-05", localDate.toString());
	}

	/**
	 * java.time -> java.util.Date
	 * 
	 * @author fixalot
	 */
	@Test
	public void parseToJavaUtilDateFromJavaTime() {
		// case#1
		logger.debug("case#1: {}", Date.from(Instant.now()).toString());

		// case#2
		logger.debug("case#2: {}", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString());

		// case#3
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
		Date out = Date.from(zdt.toInstant());
		logger.debug("case#3: {}", String.valueOf(out));

		// case#4
//		Date in = new Date();
//		LocalDateTime ldt2 = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		LocalDateTime ldt2 = LocalDateTime.now();
		Date out2 = Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
		logger.debug("case#4: {}", out2.toString());
	}

	@Test
	public void calculateDays() {
		LocalDate targetDay = LocalDate.of(2017, Month.DECEMBER, 31);
		LocalDate today = LocalDate.of(2017, Month.JANUARY, 24);
		Period period = Period.between(today, targetDay);
		long period2 = ChronoUnit.DAYS.between(today, targetDay);
		Assert.assertEquals("0 years, 11 months, 7 days later. (341 days total)", (period.getYears() + " years, " + period.getMonths()
				+ " months, " + period.getDays() + " days later. (" + period2 + " days total)"));
	}

	/**
	 * 문자열을 JavaTime 타입으로 변환
	 * 
	 * @author fixalot
	 */
	@Test
	public void parseToJavaTimeFromStringWithFormatter() {
		// 연월일 변환
		String input = "20111231";
		LocalDate date = LocalDate.parse(input, DateTimeFormatter.BASIC_ISO_DATE);
		Assert.assertEquals(LocalDate.of(2011, Month.DECEMBER, 31), date);

		// 연월일 시분초 변환
		String input2 = "2011-12-03T10:15:30";
		LocalDateTime dateTime = LocalDateTime.parse(input2, DateTimeFormatter.ISO_DATE_TIME);
		Assert.assertEquals(LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30), dateTime);

		// 연월일 시분초 변환 #2
		String input3 = "2011-12-03 10:15:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime anotherDateTime = LocalDateTime.parse(input3, formatter);
		Assert.assertEquals(LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30), anotherDateTime);
	}

	/**
	 * JavaTime 타입을 문자열로 변환하되 포매터 사용
	 * 
	 * @author fixalot
	 */
	@Test
	public void parseToStringFromJavaTimeWithFormatter() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30);
		Assert.assertEquals("2011-12-03 10:15:30", dateTime.format(formatter));
	}
}
