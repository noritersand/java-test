package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocalTime 클래스 테스트
 */
@Slf4j
public class LocalTimeTest {

    @Test
    void basicUsages() {
        LocalTime localTime = LocalTime.now();
        log.debug("localTime: {}", localTime);
        LocalTime newTime = localTime.withHour(13).withMinute(30).withSecond(13).withNano(0);
        assertThat(newTime.toString()).isEqualTo("13:30:13");
    }
}
