package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ZonedDateTime 클래스 테스트
 */
@Slf4j
public class ZonedDateTimeTest {

    /**
     * {@link ZonedDateTime}: 현재 시간과 타임존 정보를 포함한 클래스
     */
    @Test
    void basicUsages() {
        ZonedDateTime now = ZonedDateTime.now();
        log.debug("{}", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(now)); // 2023-10-18T15:18:37.7571866+09:00
        assertThat(now.getZone()).isEqualTo(ZoneId.of("Asia/Seoul"));
        assertThat(now.getZone().toString()).isEqualTo("Asia/Seoul");

        String formatted = DateTimeFormatter.ofPattern("z|Z|x|X").format(now);
        assertThat(formatted).isEqualTo("KST|+0900|+09|+09");
    }
}
