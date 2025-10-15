package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTimeFormatter 테스트 슈트
 */
@Slf4j
public class DateTimeFormatterTest {

    @Test
    void testOfPattern() {
        // LocalDateTime은 시간대나 오프셋 정보가 없음
        LocalDateTime someTime = LocalDateTime.parse("2025-10-15T11:22:33", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertThat(someTime.format(DateTimeFormatter.ofPattern("G"))).isEqualTo("AD"); // era
        assertThat(someTime.format(DateTimeFormatter.ofPattern("u"))).isEqualTo("2025"); // year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("y"))).isEqualTo("2025"); // year of era
        assertThat(someTime.format(DateTimeFormatter.ofPattern("Y"))).isEqualTo("2025"); // week based year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("M"))).isEqualTo("10"); // month of year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("L"))).isEqualTo("10"); // month of year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("g"))).isEqualTo("60963"); // modified julian day
        assertThat(someTime.format(DateTimeFormatter.ofPattern("D"))).isEqualTo("288"); // day of year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("d"))).isEqualTo("15"); // day of month
        assertThat(someTime.format(DateTimeFormatter.ofPattern("Q"))).isEqualTo("4"); // quarter of year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("q"))).isEqualTo("4"); // quarter of year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("w"))).isEqualTo("42"); // week of week based year
        assertThat(someTime.format(DateTimeFormatter.ofPattern("W"))).isEqualTo("3"); // week of month
        assertThat(someTime.format(DateTimeFormatter.ofPattern("E"))).isEqualTo("수"); // day of week
        assertThat(someTime.format(DateTimeFormatter.ofPattern("e"))).isEqualTo("4"); // localized day of week
        assertThat(someTime.format(DateTimeFormatter.ofPattern("c"))).isEqualTo("4"); // localized day of week
        assertThat(someTime.format(DateTimeFormatter.ofPattern("F"))).isEqualTo("3"); // day of week in month
        assertThat(someTime.format(DateTimeFormatter.ofPattern("h"))).isEqualTo("11"); // clock hour of am/pm (1-12)
        assertThat(someTime.format(DateTimeFormatter.ofPattern("K"))).isEqualTo("11"); // hour of am/pm (0-11)
        assertThat(someTime.format(DateTimeFormatter.ofPattern("k"))).isEqualTo("11"); // clock hour of am/pm (1-24)
        assertThat(someTime.format(DateTimeFormatter.ofPattern("H"))).isEqualTo("11"); // hour of day (0-23)
        assertThat(someTime.format(DateTimeFormatter.ofPattern("m"))).isEqualTo("22"); // minute of hour
        assertThat(someTime.format(DateTimeFormatter.ofPattern("s"))).isEqualTo("33"); // second of minute
        assertThat(someTime.format(DateTimeFormatter.ofPattern("S"))).isEqualTo("0"); // fraction of second
        assertThat(someTime.format(DateTimeFormatter.ofPattern("A"))).isEqualTo("40953000"); // milli of day
        assertThat(someTime.format(DateTimeFormatter.ofPattern("n"))).isEqualTo("0"); // nano of second
        assertThat(someTime.format(DateTimeFormatter.ofPattern("N"))).isEqualTo("40953000000000"); // nano of day
        assertThat(someTime.format(DateTimeFormatter.ofPattern("B"))).isEqualTo("오전"); // period of day
        assertThat(someTime.format(DateTimeFormatter.ofPattern("a"))).isEqualTo("오전"); // am/pm of day

        // 여기서부터는 오프셋(UTC와의 시간 차이) 정보가 필요하여 OffsetDateTime으로 변환
        OffsetDateTime offsetTime = OffsetDateTime.of(someTime.toLocalDate(), someTime.toLocalTime(), ZoneOffset.ofHours(9));
        assertThat(offsetTime.format(DateTimeFormatter.ofPattern("O"))).isEqualTo("GMT+9"); // localized zone-offset
        assertThat(offsetTime.format(DateTimeFormatter.ofPattern("X"))).isEqualTo("+09"); // zone-offset 'Z' for zero
        assertThat(offsetTime.format(DateTimeFormatter.ofPattern("x"))).isEqualTo("+09"); // zone-offset
        assertThat(offsetTime.format(DateTimeFormatter.ofPattern("Z"))).isEqualTo("+0900"); // zone-offset

        // 여기서부터는 시간대(TimeZone) 정보 필요함
        ZonedDateTime zonedTime = ZonedDateTime.of(offsetTime.toLocalDateTime(), ZoneId.of("Asia/Seoul"));
        assertThat(zonedTime.format(DateTimeFormatter.ofPattern("z"))).isEqualTo("KST"); // time-zone name
        assertThat(zonedTime.format(DateTimeFormatter.ofPattern("v"))).isEqualTo("KST"); // generic time-zone name
        assertThat(zonedTime.format(DateTimeFormatter.ofPattern("VV"))).isEqualTo("Asia/Seoul"); // time-zone ID
    }

    @Test
    void testISO() {
        // LocalDateTime은 시간대나 오프셋 정보가 없음
        LocalDateTime someTime = LocalDateTime.parse("2025-10-15T11:22:33", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertThat(someTime.format(DateTimeFormatter.BASIC_ISO_DATE)).isEqualTo("20251015");
        assertThat(someTime.format(DateTimeFormatter.ISO_LOCAL_DATE)).isEqualTo("2025-10-15");
        assertThat(someTime.format(DateTimeFormatter.ISO_DATE)).isEqualTo("2025-10-15");
        assertThat(someTime.format(DateTimeFormatter.ISO_LOCAL_TIME)).isEqualTo("11:22:33");
        assertThat(someTime.format(DateTimeFormatter.ISO_TIME)).isEqualTo("11:22:33");
        assertThat(someTime.format(DateTimeFormatter.ISO_DATE_TIME)).isEqualTo("2025-10-15T11:22:33");
        assertThat(someTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).isEqualTo("2025-10-15T11:22:33");
        assertThat(someTime.format(DateTimeFormatter.ISO_ORDINAL_DATE)).isEqualTo("2025-288");
        assertThat(someTime.format(DateTimeFormatter.ISO_WEEK_DATE)).isEqualTo("2025-W42-3");

        // 여기서부터는 오프셋 정보가 필요하여 OffsetDateTime 혹은 ZonedDateTime 필요
        OffsetDateTime offsetTime = OffsetDateTime.of(someTime, ZoneOffset.ofHours(9));
        assertThat(offsetTime.format(DateTimeFormatter.ISO_OFFSET_DATE)).isEqualTo("2025-10-15+09:00");
        assertThat(offsetTime.format(DateTimeFormatter.ISO_OFFSET_TIME)).isEqualTo("11:22:33+09:00");
        assertThat(offsetTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)).isEqualTo("2025-10-15T11:22:33+09:00");
        assertThat(offsetTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)).isEqualTo("2025-10-15T11:22:33+09:00");
        assertThat(offsetTime.format(DateTimeFormatter.ISO_INSTANT)).isEqualTo("2025-10-15T02:22:33Z");
        assertThat(offsetTime.format(DateTimeFormatter.RFC_1123_DATE_TIME)).isEqualTo("Wed, 15 Oct 2025 11:22:33 +0900");
    }
}
