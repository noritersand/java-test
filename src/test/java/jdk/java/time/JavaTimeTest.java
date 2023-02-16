package jdk.java.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class JavaTimeTest {
    public static final ZoneId ZONE_ID_ASIA_SEOUL = ZoneId.of("Asia/Seoul");
    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

    @Test
    public void test() {
        LocalDateTime now1 = LocalDateTime.now().minusHours(9);
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("UTC"));

        assertEquals(now1, now2);
    }

    /**
     * 인스턴트 만들기
     */
    @Test
    public void create() {
        log.debug("{}", Instant.now());
        log.debug("{}", LocalDate.now()); // yyyy-MM-dd
        log.debug("{}", LocalTime.now()); // HH:mm:ss.SSS

        assertEquals("1970-01-01T00:00:00Z", Instant.ofEpochMilli(0).toString());
        assertEquals("2017-09-19T06:10:46.820Z", Instant.ofEpochMilli(1505801446820L).toString());
        assertEquals("2009-02-13T23:20:23Z", Instant.ofEpochSecond(1234567223L).toString());
		assertEquals(Instant.ofEpochMilli(915152400123L), Instant.parse("1999-01-01T01:00:00.123Z")); // 표준 포맷으로 생성하기

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
        LocalDate b = LocalDate.parse("2022-10-19");
        assertTrue(a.isEqual(b));
    }

    /**
     * java.time 인스턴스의 객체 복제
     */
    @Test
    public void cloneInstance() {
        LocalDate a = LocalDate.now();
        // 이 인스턴스는 immutable하기 때문에 단순 할당만 해도 괜찮음
        LocalDate b = a;
        assertEquals(a, b);
		assertSame(a, b);
        // 여기까진 a와 b는 동일하며 동등하지만?

        // 시간이 변하는 메서드를 호출하면 그 때부턴 달라짐
        b = b.plusDays(1);
        assertNotEquals(a, b);
		assertNotSame(a, b);
    }

    @Test
    public void testYearMonth() {
        YearMonth ins = YearMonth.parse("2022-12");
        assertEquals("2022-12", ins.toString());
    }

    @Test
    public void testMonthDay() {
        MonthDay ins = MonthDay.parse("--12-31");
        assertEquals("--12-31", ins.toString());

        ins = MonthDay.parse("05-05", DateTimeFormatter.ofPattern("MM-dd"));
        assertEquals(MonthDay.parse("--05-05"), ins);
    }

    @Test
    public void testLocalDate() {
        LocalDate today = LocalDate.now();
        LocalDate ins = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);// DateTimeFormatter.ofPattern("yyyy-MM-dd")
        assertEquals("2021-01-01", ins.toString());
        assertEquals("2017-12-31", LocalDate.of(2017, Month.DECEMBER, 31).toString());
        assertEquals("2017-04-10T23:49", LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString());
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        now = now.withYear(2019).withMonth(1).withDayOfMonth(31);
        now = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        assertEquals(LocalDateTime.parse("2019-01-31T00:00:00.000"), now);

        LocalTime localTime = LocalTime.parse("15:26:45", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDate localDate = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        assertEquals("2021-01-01T15:26:45", localDateTime.toString());
    }

    @Test
    public void testLocalTime() {
        LocalTime localTime = LocalTime.now();
        log.debug("localTime: {}", localTime);
        LocalTime newTime = localTime.withHour(13).withMinute(30).withSecond(13).withNano(0);
        assertEquals("13:30:13", newTime.toString());
    }

    /**
     * 서울의 Zone ID는 Asia/Seoul
     */
    @Test
    public void testZoneIds() {
        // 사용 가능한 Zone ID 출력.
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String zone : zoneIds) {
            log.debug("zoneId: {}", zoneIds);
        }
        ZoneId zid = ZONE_ID_ASIA_SEOUL;
        assertNotNull(zid);
    }

    @Test
    public void testMinusDays() {
        LocalDate ins = LocalDate.parse("2022-10-30");
        LocalDate yesterday = ins.minusDays(1);
        assertEquals(LocalDate.parse("2022-10-29"), yesterday);
    }

    /**
     * 현재 시간을 만들 때 타임 존 지정하기
     */
    @Test
    public void setTimeZone() {
        Instant instant = Instant.now();
        LocalDateTime utc1 = LocalDateTime.ofInstant(instant, ZONE_ID_UTC);
        LocalDateTime kst1 = LocalDateTime.ofInstant(instant, ZONE_ID_ASIA_SEOUL);
        assertEquals(utc1, kst1.minusHours(9));

        LocalDateTime utc2 = LocalDateTime.now(ZONE_ID_UTC);
        LocalDateTime kst2 = LocalDateTime.now(ZONE_ID_ASIA_SEOUL);
        assertEquals(utc2, kst2.minus(Duration.ofHours(9)));
    }

    /**
     * 이미 만들어진 시간 객체의 타임 존 변경하기
     */
    @Test
    public void changeTimeZone() {
        // 이건 뭔가 이상함. 이미 만들어진 인스턴스의 타임존을 바꿨으나 시간은 그대로
        LocalDateTime utc3 = LocalDateTime.now(ZONE_ID_UTC);
        ZonedDateTime kst3 = utc3.atZone(ZONE_ID_ASIA_SEOUL);
        assertEquals(ZONE_ID_ASIA_SEOUL, kst3.getZone());
        assertEquals(utc3, kst3.toLocalDateTime());

        // TODO 잘 안됨
//		LocalDateTime kst4 = LocalDateTime.now(ZONE_ID_ASIA_SEOUL);
//		log.debug("kst4: {}", kst4);
//		Instant instant1 = kst4.toInstant(ZoneOffset.ofHours(-9));
//		log.debug("instant1: {}", instant1);
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
    public void testGetOffsetDateTime() {
        assertEquals("2017-12-31T23:59:59.999999999Z",
                OffsetDateTime.of(LocalDate.of(2017, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC).toString());
        assertEquals("2017-01-14T10:20:30Z", OffsetDateTime.of(2017, 1, 14, 10, 20, 30, 0, ZoneOffset.UTC).toString());
    }

    @Test
    public void testGetOffsetTime() {
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

    @Test
    public void parseMonthDayToLocalDate() {
        MonthDay monthDay = MonthDay.parse("--12-31");
        LocalDate localDate = LocalDate.of(2022, monthDay.getMonth(), monthDay.getDayOfMonth());
        assertEquals(LocalDate.parse("2022-12-31"), localDate);
    }

    /**
     * JavaTime 타입을 문자열로 변환하되 포매터 사용
     *
     * @author fixalot
     */
    @Test
    public void parseJavaTimeToStringWithFormatter() {
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
    public void parseStringToJavaTimeWithFormatter() {
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
     * LocalDateTime -> LocalDate
     */
    @Test
    public void parseLocalDateTimeToLocalDate() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-12-31T00:00:00.000");
        LocalDate localDate = localDateTime.toLocalDate();
        assertEquals(LocalDate.parse("2022-12-31"), localDate);
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
    public void parseJavaUtilDateToStringFrom() {
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
    public void parseJavaTimeToJavaUtilDate() {
        // case#1
        log.debug("case#1: {}", Date.from(Instant.now()));

        // case#2
        log.debug("case#2: {}", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        // case#3
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date out = Date.from(zdt.toInstant());
        log.debug("case#3: {}", out);

        // case#4
//		Date in = new Date();
//		LocalDateTime ldt2 = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldt2 = LocalDateTime.now();
        Date out2 = Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
        log.debug("case#4: {}", out2);
    }

    @Test
    public void parseLocalDateTimeToTimestamp() {
        LocalDateTime ldt = LocalDateTime.parse("2019-01-31T00:00:00.000");
        Timestamp ts = Timestamp.valueOf(ldt);
        log.debug("ts: {}", ts);
    }

    @Test
    public void parseTimestampToLocalDateTime() {
        Timestamp timestamp = Timestamp.valueOf("2022-11-25 12:45:00.000");
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        assertEquals(LocalDateTime.parse("2022-11-25T12:45:00.000"), localDateTime);
    }
}
