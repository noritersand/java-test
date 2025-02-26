package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO class description
 */
@Slf4j
public class LocalDateTimeTest {

    /**
     * <p>여기서 사용하는 <code>LocalDateTime.now(ZoneId.of("UTC"))</code> 코드는, localDateTime에 시간대를 설정하는 게 아니라,  ZoneId를 인자로 받아 해당 시간대에 맞는 현지(local)의 현재 시간을 계산하는 것이라 이해하면 된다.</p>
     * <p>그리고 반환되는 LocalDateTime 인스턴스에는 시간대 정보가 없다. 애초에 {@link LocalDateTime} 타입은 날짜와 시간 정보만 다루는 타입이다.</p>
     */
    @Test
    void basicUsages() {
        LocalDateTime now1 = LocalDateTime.now().minusHours(9);
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("UTC")); // <-- doc comment 참고
        assertThat(now2.isEqual(now1)).isTrue();

        LocalDateTime ldt1 = LocalDateTime.now();
        ldt1 = ldt1.withYear(2019).withMonth(1).withDayOfMonth(31);
        ldt1 = ldt1.withHour(0).withMinute(0).withSecond(0).withNano(0);
        assertThat(ldt1).isEqualTo(LocalDateTime.parse("2019-01-31T00:00:00.000"));

        LocalTime localTime = LocalTime.parse("15:26:45", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDate localDate = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        assertThat(localDateTime.toString()).isEqualTo("2021-01-01T15:26:45");

        LocalDateTime ldt2 = LocalDateTime.ofEpochSecond(1724057691L, 0, ZoneOffset.of("+09:20"));
        assertThat(ldt2.toString()).isEqualTo("2024-08-19T18:14:51");

        LocalDateTime ldt3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(1724057691992L),
                ZoneId.of("Asia/Seoul"));
        assertThat(ldt3.toString()).isEqualTo("2024-08-19T17:54:51.992");
    }
}
