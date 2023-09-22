package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class JavaTimeTest {
    public static final ZoneId ZONE_ID_ASIA_SEOUL = ZoneId.of("Asia/Seoul");
    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

    @Test
    void YearMonthType() {
        YearMonth ins = YearMonth.parse("2022-09");
        assertThat(ins.toString()).isEqualTo("2022-09");

        String yyyyMM = ins.format(DateTimeFormatter.ofPattern("yyyyMM"));
        assertThat(yyyyMM).isEqualTo("202209");

        assertThat(ins.getYear()).isEqualTo(2022);
        assertThat(ins.getMonth()).isEqualTo(Month.SEPTEMBER);
        assertThat(ins.getMonthValue()).isEqualTo(9);
    }

    @Test
    void LocalDateType() {
        LocalDate today = LocalDate.now();
        LocalDate ins = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);// DateTimeFormatter.ofPattern("yyyy-MM-dd")

        assertThat(ins.getYear()).isEqualTo(2021);
        assertThat(ins.getMonthValue()).isEqualTo(1);
        assertThat(ins.toString()).isEqualTo("2021-01-01");
        assertThat(LocalDate.of(2017, Month.DECEMBER, 31).toString()).isEqualTo("2017-12-31");
        assertThat(LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString()).isEqualTo("2017-04-10T23:49");
    }

    @Test
    void LocalDateTimeType() {
        LocalDateTime now = LocalDateTime.now();
        now = now.withYear(2019).withMonth(1).withDayOfMonth(31);
        now = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        assertThat(now).isEqualTo(LocalDateTime.parse("2019-01-31T00:00:00.000"));

        LocalTime localTime = LocalTime.parse("15:26:45", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDate localDate = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        assertThat(localDateTime.toString()).isEqualTo("2021-01-01T15:26:45");
    }

    @Test
    void LocalTimeType() {
        LocalTime localTime = LocalTime.now();
        log.debug("localTime: {}", localTime);
        LocalTime newTime = localTime.withHour(13).withMinute(30).withSecond(13).withNano(0);
        assertThat(newTime.toString()).isEqualTo("13:30:13");
    }

    @Test
    void DayOfWeekType() {
        LocalDate instance = LocalDate.parse("2022-12-31");
        DayOfWeek dayOfWeek = instance.getDayOfWeek();
        assertThat(dayOfWeek.ordinal()).isEqualTo(5);
        assertThat(dayOfWeek.getValue()).isEqualTo(6);
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(DayOfWeek.MONDAY.getValue()).isEqualTo(1);
        assertThat(DayOfWeek.MONDAY.ordinal()).isEqualTo(0);
    }

    @Test
    void DurationType() {
        LocalDateTime dateTime1 = LocalDateTime.of(2023, 1, 1, 01, 12, 05);
        LocalDateTime dateTime2 = LocalDateTime.of(2023, 2, 3, 15, 38, 59);

        Duration duration = Duration.between(dateTime1, dateTime2);

        int totalSeconds = (int) duration.getSeconds();

        // 총 시간을 일, 시간, 분, 초로 변환
        int days = totalSeconds / (24 * 3600);
        int remainingSeconds = totalSeconds % (24 * 3600);

        int hours = remainingSeconds / 3600;
        remainingSeconds = remainingSeconds % 3600;

        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;

        assertThat(days).isEqualTo(33L);
        assertThat(hours).isEqualTo(14L);
        assertThat(minutes).isEqualTo(26L);
        assertThat(seconds).isEqualTo(54L);
        System.out.printf("Elapsed time: %d days, %d hours, %d minutes, %d seconds%n", days, hours, minutes, seconds);
    }

    /**
     * 서울의 Zone ID는 Asia/Seoul
     */
    @Test
    void ZoneIdType() {
        // 사용 가능한 Zone ID 출력.
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String zone : availableZoneIds) {
            log.debug("zoneId: {}", availableZoneIds);
        }
        ZoneId zid = ZONE_ID_ASIA_SEOUL;
        assertThat(zid).isNotNull();
    }

    @Test
    void test() {
        LocalDateTime now1 = LocalDateTime.now().minusHours(9);
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("UTC"));

        assertThat(now2).isEqualTo(now1);
    }

    /**
     * 인스턴트 만들기
     */
    @Test
    void create() {
        log.debug("{}", Instant.now());
        log.debug("{}", LocalDate.now()); // yyyy-MM-dd
        log.debug("{}", LocalTime.now()); // HH:mm:ss.SSS

        assertThat(Instant.ofEpochMilli(0).toString()).isEqualTo("1970-01-01T00:00:00Z");
        assertThat(Instant.ofEpochMilli(1505801446820L).toString()).isEqualTo("2017-09-19T06:10:46.820Z");
        assertThat(Instant.ofEpochSecond(1234567223L).toString()).isEqualTo("2009-02-13T23:20:23Z");
        assertThat(Instant.parse("1999-01-01T01:00:00.123Z")).isEqualTo(Instant.ofEpochMilli(915152400123L)); // 표준 포맷으로 생성하기

        // create instance from ISO date time string
        assertThat(Instant.parse("2017-04-18T01:24:48.842Z").toString()).isEqualTo("2017-04-18T01:24:48.842Z");
    }

    /**
     * 날짜 비교
     */
    @Test
    void comparing() {
        LocalDate beginingOfWorld = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endOfUs = LocalDate.parse("9999-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        assertThat(beginingOfWorld.isEqual(endOfUs)).isFalse();
        assertThat(beginingOfWorld.isBefore(endOfUs)).isTrue();
        assertThat(endOfUs.isAfter(beginingOfWorld)).isTrue();

        LocalDate a = LocalDate.parse("2022-10-19", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate b = LocalDate.parse("2022-10-19");
        assertThat(a.isEqual(b)).isTrue();
    }

    /**
     * java.time 인스턴스의 객체 복제
     */
    @Test
    void cloneInstance() {
        LocalDate a = LocalDate.now();
        // 이 인스턴스는 immutable하기 때문에 단순 할당만 해도 괜찮음
        LocalDate b = a;
        assertThat(b).isEqualTo(a);
        assertThat(b).isSameAs(a);
        // 여기까진 a와 b는 동일하며 동등하지만?

        // 시간이 변하는 메서드를 호출하면 그 때부턴 달라짐
        b = b.plusDays(1);
        assertThat(b).isNotEqualTo(a);
        assertThat(b).isNotSameAs(a);
    }

    @Test
    void testMonthDay() {
        MonthDay ins = MonthDay.parse("--12-31");
        assertThat(ins.toString()).isEqualTo("--12-31");

        ins = MonthDay.parse("05-05", DateTimeFormatter.ofPattern("MM-dd"));
        assertThat(ins).isEqualTo(MonthDay.parse("--05-05"));
    }

    @Test
    void testMinusDays() {
        LocalDate ins = LocalDate.parse("2022-10-30");
        LocalDate yesterday = ins.minusDays(1);
        assertThat(yesterday).isEqualTo(LocalDate.parse("2022-10-29"));
    }

    /**
     * 현재 시간을 만들 때 타임 존 지정하기
     */
    @Test
    void setTimeZone() {
        Instant instant = Instant.now();
        LocalDateTime utc1 = LocalDateTime.ofInstant(instant, ZONE_ID_UTC);
        LocalDateTime kst1 = LocalDateTime.ofInstant(instant, ZONE_ID_ASIA_SEOUL);
        assertThat(kst1.minusHours(9)).isEqualTo(utc1);

        LocalDateTime utc2 = LocalDateTime.now(ZONE_ID_UTC);
        LocalDateTime kst2 = LocalDateTime.now(ZONE_ID_ASIA_SEOUL);
        assertThat(kst2.minus(Duration.ofHours(9))).isEqualTo(utc2);
    }

    /**
     * 이미 만들어진 시간 객체의 타임 존 변경하기
     */
    @Test
    void changeTimeZone() {
        // 이건 뭔가 이상함. 이미 만들어진 인스턴스의 타임존을 바꿨으나 시간은 그대로
        LocalDateTime utc3 = LocalDateTime.now(ZONE_ID_UTC);
        ZonedDateTime kst3 = utc3.atZone(ZONE_ID_ASIA_SEOUL);
        assertThat(kst3.getZone()).isEqualTo(ZONE_ID_ASIA_SEOUL);
        assertThat(kst3.toLocalDateTime()).isEqualTo(utc3);

        // TODO 잘 안됨
//		LocalDateTime kst4 = LocalDateTime.now(ZONE_ID_ASIA_SEOUL);
//		log.debug("kst4: {}", kst4);
//		Instant instant1 = kst4.toInstant(ZoneOffset.ofHours(-9));
//		log.debug("instant1: {}", instant1);
    }

    @Test
    void testGetOffsetDateTime() {
        assertThat(OffsetDateTime.of(LocalDate.of(2017, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC).toString()).isEqualTo("2017-12-31T23:59:59.999999999Z");
        assertThat(OffsetDateTime.of(2017, 1, 14, 10, 20, 30, 0, ZoneOffset.UTC).toString()).isEqualTo("2017-01-14T10:20:30Z");
    }

    @Test
    void testGetOffsetTime() {
        assertThat(OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MAX).toString()).isEqualTo("22:58+18:00");
        assertThat(OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MIN).toString()).isEqualTo("22:58-18:00");
        assertThat(OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.UTC).toString()).isEqualTo("22:58Z");
    }

    @Test
    void parseMonthDayToLocalDate() {
        MonthDay monthDay = MonthDay.parse("--12-31");
        LocalDate localDate = LocalDate.of(2022, monthDay.getMonth(), monthDay.getDayOfMonth());
        assertThat(localDate).isEqualTo(LocalDate.parse("2022-12-31"));
    }

    /**
     * JavaTime 타입을 문자열로 변환하되 포매터 사용
     *
     * @author fixalot
     */
    @Test
    void parseJavaTimeToStringWithFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30);
        assertThat(dateTime.format(formatter)).isEqualTo("2011-12-03 10:15:30");
    }

    /**
     * 문자열을 JavaTime 타입으로 변환
     *
     * @author fixalot
     */
    @Test
    void parseStringToLocalDate() {
        // 연월일 변환
        String input11 = "20111231";
        LocalDate date11 = LocalDate.parse(input11, DateTimeFormatter.BASIC_ISO_DATE);
        assertThat(date11).isEqualTo(LocalDate.of(2011, Month.DECEMBER, 31));

        // 연월일 변환 #2
        String input12 = "2022-01-02";
        LocalDate date12 = LocalDate.parse(input12, DateTimeFormatter.ISO_LOCAL_DATE);
        assertThat(date12).isEqualTo(LocalDate.of(2022, Month.JANUARY, 2));
    }

    @Test
    void parseStringToLocalDateTime() {
        // 연월일 시분초 변환
        String input21 = "2011-12-03T10:15:30";
        LocalDateTime dateTime = LocalDateTime.parse(input21, DateTimeFormatter.ISO_DATE_TIME);
        assertThat(dateTime).isEqualTo(LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30));

        // 연월일 시분초 변환 #2
        String input22 = "2011-12-03 10:15:30";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime anotherDateTime = LocalDateTime.parse(input22, formatter2);
        assertThat(anotherDateTime).isEqualTo(LocalDateTime.of(2011, Month.DECEMBER, 03, 10, 15, 30));
    }

    /**
     * LocalDate -> java.sql.Date
     */
    @Test
    void parseLocalDateToSqlDate() {
        LocalDate a = LocalDate.of(2017, Month.DECEMBER, 31);
        java.sql.Date b = java.sql.Date.valueOf(a);
        assertThat(b.toString()).isEqualTo("2017-12-31");
    }

    /**
     * LocalDateTime -> LocalDate
     */
    @Test
    void parseLocalDateTimeToLocalDate() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-12-31T00:00:00.000");
        LocalDate localDate = localDateTime.toLocalDate();
        assertThat(localDate).isEqualTo(LocalDate.parse("2022-12-31"));
    }

    /**
     * java.sql.Date -> LocalDate
     */
    @Test
    void parseSqlDateToLocalDate() {
        java.sql.Date a = java.sql.Date.valueOf("2020-12-31");
        LocalDate b = a.toLocalDate();
        assertThat(b.toString()).isEqualTo("2020-12-31");
    }

    /**
     * java.util.Date -> java.time -> String
     *
     * @author fixalot
     */
    @Test
    void parseJavaUtilDateToJavaTimeToString() {
        Calendar input = new GregorianCalendar(2016, 2, 5);
        Date date = input.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertThat(localDate.toString()).isEqualTo("2016-03-05");
    }

    /**
     * java.time -> java.util.Date
     *
     * @author fixalot
     */
    @Test
    void parseJavaTimeToJavaUtilDate() {
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
    void parseLocalDateTimeToTimestamp() {
        LocalDateTime ldt = LocalDateTime.parse("2019-01-31T00:00:00.000");
        Timestamp ts = Timestamp.valueOf(ldt);
        log.debug("ts: {}", ts);
    }

    @Test
    void parseTimestampToLocalDateTime() {
        Timestamp timestamp = Timestamp.valueOf("2022-11-25 12:45:00.000");
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        assertThat(localDateTime).isEqualTo(LocalDateTime.parse("2022-11-25T12:45:00.000"));
    }

    @Test
    void generateCalendar() {
        List<LocalDate> dates = new LinkedList<>();
        LocalDate currentDate = LocalDate.of(2023, 4, 01);
        while (!currentDate.isAfter(LocalDate.of(2023, 5, 31))) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        log.debug("{}", dates);
        assertThat(dates.size()).isEqualTo(61);
        assertThat(dates.get(0)).isEqualTo(LocalDate.of(2023, 4, 01));
        assertThat(dates.get(dates.size() - 1)).isEqualTo(LocalDate.of(2023, 5, 31));
    }


    /**
     * <p>특정일이 어느 월의 몇 번째 주인지 계산하기</p>
     * <br>
     * <h2>원하는 값</h2>
     * <p>예를 들어  2023년 5월 29일은 월요일인데, 그 날짜 다음에 오는 목요일은 6월에 속한다.</p>
     * <p>그러면 5월 29일은 5월의 다섯 번째 주가 아니라 6월의 첫 번째 주다.</p>
     * <h2>현재까지 구한 값</h2>
     * <p>어느 월인지는 가져왔으나 그 날이 정확히 몇 번째 주인지는 아직 모름</p>
     * <p>ChronoField를 쓰면 계산을 이상하게 한다(시작일부터 7일을 단순 계산해서 7일 이전이면 첫 째 주, 7일 후면 둘 째 주 이상이 됨</p>
     */
    @Test
    void testCalculateWeekOfMonthByDay() {
//        dayOfWeek.get(TemporalAdjusters)
        assertThat(getThursdayOfWeek(LocalDate.of(2023, 5, 8))).isEqualTo(LocalDate.of(2023, 5, 11));
        assertThat(getThursdayOfWeek(LocalDate.of(2023, 5, 21))).isEqualTo(LocalDate.of(2023, 5, 18));
        assertThat(getThursdayOfWeek(LocalDate.of(2023, 5, 31))).isEqualTo(LocalDate.of(2023, 6, 1));

        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2023, 5, 29))).isEqualTo(new int[]{6, 1}); // 6월의 첫 째 주
        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2023, 5, 1))).isEqualTo(new int[]{5, 1}); // 5월의 첫 째 주
        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2024, 12, 30))).isEqualTo(new int[]{1, 1}); // 1월의 첫 째 주
        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2025, 1, 16))).isEqualTo(new int[]{1, 3}); // 1월의 셋 째 주
        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2025, 2, 1))).isEqualTo(new int[]{1, 5}); // 2월의 둘 째 주
        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2025, 2, 1))).isEqualTo(new int[]{1, 5}); // 2월의 둘 째 주

        // FIXME 여기서 틀림
        // 2025-02-01은 2월의 첫 째 주가 아니라 1월의 마지막 주다.
        // 따라서 2025-02-08은 2월의 첫 째 주가 되어야 함.
//        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2025, 2, 8)))
//                .isEqualTo(new int[]{2, 1}); // 2월의 첫 째 주

        assertThat(calculateWeekOfMonthByDay(LocalDate.of(2025, 2, 14))).isEqualTo(new int[]{2, 2}); // 2월의 둘 째 주
    }

    private LocalDate getThursdayOfWeek(LocalDate input) {
        LocalDate thursdayOfWeek = input.getDayOfWeek().getValue() < DayOfWeek.THURSDAY.getValue()
                ? input.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
                : input.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        return thursdayOfWeek;
    }

    private int[] calculateWeekOfMonthByDay(LocalDate input) {
        LocalDate thursdayOfWeek = getThursdayOfWeek(input);
        int[] result = new int[2];
        if (input.getMonthValue() == thursdayOfWeek.getMonthValue()) {
            result[0] = input.getMonthValue();
            result[1] = input.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
        } else {
            result[0] = thursdayOfWeek.getMonthValue();
            result[1] = thursdayOfWeek.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
        }
        return result;
    }

}
