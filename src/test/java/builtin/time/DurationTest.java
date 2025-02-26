package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Duration 클래스 테스트
 */
@Slf4j
public class DurationTest {

    /**
     * <p>시간과 시간 사이의 간격을 다루는 Duration 타입 테스트</p>
     * <p>Duration 값을 날짜 데이터 없이 시간으로만 표현하려면 {@link testbed.time.TimeSegment}를 볼 것</p>
     */
    @Test
    void basicUsages() {
        Duration d1 = Duration.ofSeconds(3600);
        assertThat(d1.getSeconds()).isEqualTo(3600L);

        Duration d2 = Duration.ofMinutes(60);
        assertThat(d2.getSeconds()).isEqualTo(3600L);

        Duration d3 = Duration.ofHours(1);
        assertThat(d3.getSeconds()).isEqualTo(3600L);

        Duration d4 = Duration.of(123, ChronoUnit.SECONDS);
        assertThat(d4).isNotSameAs(Duration.ofSeconds(123)); // 서로 다른 인스턴스
        assertThat(d4).isEqualTo(Duration.ofSeconds(123));
    }

    @Test
    void calculateElapsedTime() {
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
}
