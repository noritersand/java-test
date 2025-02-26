package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Instant 클래스 테스트
 */
@Slf4j
public class InstantTest {
    private static final String ID_ASIA_SEOUL = "Asia/Seoul";
    private static final String ID_UTC = "UTC";
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * <p>{@link Instant}: java.time 패키지에서 날짜/시간 데이터의 기반이 되는 타입. UTC 시간대를 기준으로 현재 시간을 나타낸다.</p>
     * <p>{@link LocalDateTime}, {@link ZonedDateTime}, {@link OffsetDateTime} 등의 클래스는 내부적으로 {@link Instant}를 사용하여 UTC 기준 시간을 관리하고, 이를 바탕으로 해당 지역의 시간대에 맞게 날짜와 시간을 계산한다</p>
     * <p>에포크 시간(Epoch time, 1970-01-01 00:00)으로부터의 시간을 초와 나노초 단위로 표현할 수 있다.</p>
     * <p>Instant는 {@link LocalDateTime}이나 {@link LocalDate}처럼 시간대 정보를 포함하지 않는다.</p>
     */
    @Test
    void basicUsages() {
        Instant inst1 = Instant.ofEpochSecond(0);
        assertThat(inst1.toString()).isEqualTo("1970-01-01T00:00:00Z");

        Instant inst2 = Instant.ofEpochSecond(1701665920L);
        assertThat(inst2.toString()).isEqualTo("2023-12-04T04:58:40Z");

        Instant inst3 = Instant.now();
        log.debug("inst3.toString(): {}", inst3.toString()); // 2023-12-04T05:03:05.086102800Z

        // 이 시간은
        Instant inst4 = Instant.now(Clock.system(ZoneId.of(ID_UTC)));
        log.debug("inst4.toString(): {}", inst4.toString());

        // 이 시간과 차이가 없음
        Instant inst5 = Instant.now(Clock.system(ZoneId.of(ID_ASIA_SEOUL)));
        log.debug("inst5.toString(): {}", inst5.toString());

        // 서울 시간대의 Clock을 넘겨도 어차피 UTC 기준으로 시간값을 반환하며, Instant.now()와 차이 없음.
        String formatted = LocalDateTime.ofInstant(inst4, ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(yyyyMMddHHmmss));
        String formatted1 = LocalDateTime.ofInstant(inst5, ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(yyyyMMddHHmmss));
        assertThat(formatted).isEqualTo(formatted1);

        // -----------------
        // 에포크 시간 구하기

        log.debug("inst5.toEpochMilli(): {}", inst5.toEpochMilli()); // 에포크 시간을 밀리초로
        assertThat(inst5.toEpochMilli()).isGreaterThan(0); // 에포크 시간보다 무조건 큼

        log.debug("inst5.getEpochSecond(): {}", inst5.getEpochSecond()); // 에포크 시간을 초로
        log.debug("inst5.getNano(): {}", inst5.getNano()); // 나노초 출력. 이 값은 보정용으로 쓰이는듯함

        // 이건 뭘까. 아마도 시간값에서 밀리초만 자른거?
        log.debug("inst5.getLong(ChronoField.MILLI_OF_SECOND): {}", inst5.getLong(ChronoField.MILLI_OF_SECOND));;
    }
}
