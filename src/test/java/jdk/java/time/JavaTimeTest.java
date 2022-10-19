package jdk.java.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
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

	/**
	 * 인스턴트 만들기
	 */
	@Test
	public void create() {
		logger.debug("{}", Instant.now());
		logger.debug("{}", LocalDate.now()); // yyyy-MM-dd
		logger.debug("{}", LocalTime.now()); // HH:mm:ss.SSS

		assertEquals("1970-01-01T00:00:00Z", Instant.ofEpochMilli(0).toString());
		assertEquals("2017-09-19T06:10:46.820Z", Instant.ofEpochMilli(1505801446820L).toString());
		assertEquals("2009-02-13T23:20:23Z", Instant.ofEpochSecond(1234567223L).toString());
		assertTrue(Instant.ofEpochMilli(915152400123L).equals(Instant.parse("1999-01-01T01:00:00.123Z"))); // 표준 포맷으로 생성하기

		// create instance from ISO date time string
		assertEquals("2017-04-18T01:24:48.842Z", Instant.parse("2017-04-18T01:24:48.842Z").toString());
	}

	/**
	 * 날짜 비교
	 */
	@Test
	public void comparing() {
		LocalDate beginingOfWorld = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate endOfUs = LocalDate.parse("9999-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
		assertFalse(beginingOfWorld.isEqual(endOfUs));
		assertTrue(beginingOfWorld.isBefore(endOfUs));
		assertTrue(endOfUs.isAfter(beginingOfWorld));

		LocalDate a = LocalDate.parse("2022-10-19", DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate b = LocalDate.now();
		assertTrue(a.isEqual(b));
	}

	/**
	 * 서울의 Zone ID는 Asia/Seoul
	 */
	@Test
	public void testZoneIds() {
		// 사용 가능한 Zone ID 출력.
		Set<String> zoneIds= ZoneId.getAvailableZoneIds();
		for (String zone : zoneIds) {
			logger.debug("zoneId: {}", zoneIds);
		}
		ZoneId zid = ZoneId.of("Asia/Seoul");
		assertNotNull(zid);
	}

	@Test
	public void YearMonth() {
		YearMonth ins = YearMonth.parse("2022-12");
		assertEquals("2022-12", ins.toString());
	}

	@Test
	public void MonthDay() {
		MonthDay ins = MonthDay.parse("--12-31");
		assertEquals("--12-31", ins.toString());
	}

	@Test
	public void LocalDate() {
		LocalDate today = LocalDate.now();
		LocalDate ins = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);// DateTimeFormatter.ofPattern("yyyy-MM-dd")
		assertEquals("2021-01-01", ins.toString());
		assertEquals("2017-12-31", LocalDate.of(2017, Month.DECEMBER, 31).toString());
		assertEquals("2017-04-10T23:49", LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString());
	}

	@Test
	public void LocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		now = now.withYear(2019).withMonth(1).withDayOfMonth(31);
		now = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
		assertEquals(LocalDateTime.parse("2019-01-31T00:00:00.000"), now);
	}

	@Test
	public void testGetDayOfWeek() {
		LocalDate instance = LocalDate.parse("2022-12-31");
		DayOfWeek dayOfWeek = instance.getDayOfWeek();
		assertEquals(DayOfWeek.SATURDAY, dayOfWeek);
		assertEquals(5, dayOfWeek.ordinal());
		assertEquals(6, dayOfWeek.getValue());
	}

	@Test
	public void getOffsetDateTime() {
		assertEquals("2017-12-31T23:59:59.999999999Z",
				OffsetDateTime.of(LocalDate.of(2017, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC).toString());
		assertEquals("2017-01-14T10:20:30Z", OffsetDateTime.of(2017, 1, 14, 10, 20, 30, 0, ZoneOffset.UTC).toString());
	}

	@Test
	public void getOffsetTime() {
		assertEquals("22:58+18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MAX).toString());
		assertEquals("22:58-18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MIN).toString());
		assertEquals("22:58Z", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.UTC).toString());
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
		assertEquals("[2016-03-05, 2016-03-06, 2016-03-07, 2016-03-08, 2016-03-09, 2016-03-10]", list.toString());
	}

	@Test
	public void calculateDays() {
		LocalDate targetDay = LocalDate.of(2017, Month.DECEMBER, 31);
		LocalDate today = LocalDate.of(2017, Month.JANUARY, 24);
		Period period = Period.between(today, targetDay);
		long period2 = ChronoUnit.DAYS.between(today, targetDay);
		assertEquals("0 years, 11 months, 7 days later. (341 days total)", (period.getYears() + " years, " + period.getMonths()
				+ " months, " + period.getDays() + " days later. (" + period2 + " days total)"));
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
		assertEquals("2011-12-03 10:15:30", dateTime.format(formatter));
	}

	/**
	 * 문자열을 JavaTime 타입으로 변환
	 *
	 * @author fixalot
	 */
	@Test
	public void parseToJavaTimeFromStringWithFormatter() {
		// 연월일 변환
		String input11 = "20111231";
		LocalDate date11 = LocalDate.parse(input11, DateTimeFormatter.BASIC_ISO_DATE);
		assertEquals(LocalDate.of(2011, Month.DECEMBER, 31), date11);

		// 연월일 변환 #2
		String input12 = "2022-01-02";
		LocalDate date12 = LocalDate.parse(input12, DateTimeFormatter.ISO_LOCAL_DATE);
		assertEquals(LocalDate.of(2022, Month.JANUARY, 2), date12);

		// 연월일 변환 #3 SimpleDateFormat으로
		String input13 = "2023-05-10";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		LocalDate.parse(input13, sdf); // 응 안됨

		// 연월일 시분초 변환
		String input21 = "2011-12-03T10:15:30";
		LocalDateTime dateTime = LocalDateTime.parse(input21, DateTimeFormatter.ISO_DATE_TIME);
		assertEquals(LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30), dateTime);

		// 연월일 시분초 변환 #2
		String input22 = "2011-12-03 10:15:30";
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime anotherDateTime = LocalDateTime.parse(input22, formatter2);
		assertEquals(LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30), anotherDateTime);
	}

	/**
	 * LocalDate -> java.sql.Date
	 */
	@Test
	public void parseLocalDateToSqlDate() {
		LocalDate a = LocalDate.of(2017, Month.DECEMBER, 31);
		java.sql.Date b = java.sql.Date.valueOf(a);
		assertEquals("2017-12-31", b.toString());
	}

	/**
	 * java.sql.Date -> LocalDate
	 */
	@Test
	public void parseSqlDateToLocalDate() {
		java.sql.Date a = java.sql.Date.valueOf("2020-12-31");
		LocalDate b = a.toLocalDate();
		assertEquals("2020-12-31", b.toString());
	}

	/**
	 * java.util.Date -> java.time -> String
	 *
	 * @author fixalot
	 */
	@Test
	public void parseToStringFromJavaUtilDate() {
		Calendar input = new GregorianCalendar(2016, 2, 5);
		Date date = input.getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		assertEquals("2016-03-05", localDate.toString());
	}

	/**
	 * java.time -> java.util.Date
	 *
	 * @author fixalot
	 */
	@Test
	public void parseToJavaUtilDateFromJavaTime() {
		// case#1
		logger.debug("case#1: {}", Date.from(Instant.now()));

		// case#2
		logger.debug("case#2: {}", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		// case#3
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
		Date out = Date.from(zdt.toInstant());
		logger.debug("case#3: {}", out);

		// case#4
//		Date in = new Date();
//		LocalDateTime ldt2 = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		LocalDateTime ldt2 = LocalDateTime.now();
		Date out2 = Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
		logger.debug("case#4: {}", out2);
	}

}
