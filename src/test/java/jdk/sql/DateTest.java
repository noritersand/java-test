package jdk.sql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Date 테스트
 *
 * @author fixalot
 * @since 2019-01-11
 */
@Slf4j
public class DateTest {
    public static final ZoneId ZONE_ID_ASIA_SEOUL = ZoneId.of("Asia/Seoul");
    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

    @Test
    void shouldBeEqual() {
        long mills = System.currentTimeMillis();
        log.debug("currentTimeMills: {}", mills);
        Date date = new Date(mills);
        assertEquals(mills, date.getTime());
    }

    @Test
    void testJavaSqlDate() {
        Date date = new Date(1547168374396L);
        assertEquals("2019-01-11", date.toString());

        Calendar calendar = Calendar.getInstance();

        Date ins1 = new Date(calendar.getTimeInMillis());
        log.debug("ins1: {}", ins1);

        java.util.Date ins2 = calendar.getTime();
        log.debug("ins2: {}", ins2);
    }

    @Test
    void testJavaSqlTime() {
        Time time = new Time(1547168374396L);
        assertEquals("09:59:34", time.toString());

    }

    @Test
    void testJavaSqlTimestamp() {
        Timestamp timestamp = new Timestamp(1547168374396L);
        assertEquals("2019-01-11 09:59:34.396", timestamp.toString());
    }

    @Test
    void changeTimeZone() {
        Date date = new Date(1547168374396L);
        assertEquals("2019-01-11", date.toString());

        // java.sql.Date는 시간을 지원하지 않아서 항상 예외 발생
        assertThrows(UnsupportedOperationException.class, () -> {
            date.toInstant();
        });

        // .toInstant()를 쓰고 싶으면 time component 가 있는 클래스를 써야함
        Instant instant = new Timestamp(1547168374396L).toInstant();
        LocalDateTime utc1 = LocalDateTime.ofInstant(instant, ZONE_ID_UTC);
        LocalDateTime kst1 = LocalDateTime.ofInstant(instant, ZONE_ID_ASIA_SEOUL);
        assertEquals(utc1, kst1.minusHours(9));
    }
}
