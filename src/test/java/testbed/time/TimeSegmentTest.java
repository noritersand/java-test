package testbed.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

/**
 * 시간 차이를 의미하는 {@link Duration}과, 이를 편하게 쓰려고 만든 클래스인 {@link TimeSegment} 테스트 슈트
 *
 * @author fixalot
 * @since 2023-11-01
 */
@Slf4j
class TimeSegmentTest {

    @Test
    void withDuration() {
        LocalDateTime dateTime1 = LocalDateTime.of(2023, 1, 1, 01, 12, 05);
        LocalDateTime dateTime2 = LocalDateTime.of(2023, 1, 2, 02, 24, 05);

        Duration duration = Duration.between(dateTime1, dateTime2);

        TimeSegment timeSegment = new TimeSegment(duration);
        assertThat(timeSegment.getHours()).isEqualTo(25);
        assertThat(timeSegment.getMinuteOfHour()).isEqualTo(12);
        assertThat(timeSegment.getSecondOfMinute()).isEqualTo(0);
    }

    @Test
    void withSeconds() {
        TimeSegment timeSegment = new TimeSegment(3604);
        assertThat(timeSegment.getHours()).isEqualTo(1);
        assertThat(timeSegment.getMinuteOfHour()).isEqualTo(0);
        assertThat(timeSegment.getSecondOfMinute()).isEqualTo(4);
    }

}
