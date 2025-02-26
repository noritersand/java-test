package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * OffSetDateTime 클래스 테스트
 */
@Slf4j
public class OffSetDateTimeTest {

    /**
     * {@link OffsetDateTime}: 현재 시간과 오프셋 정보를 포함한 클래스
     */
    @Test
    void basicUsages() {
        OffsetDateTime now = OffsetDateTime.now();
        log.debug("now: {}", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(now)); // 2023-10-18T17:06:54.8219252+09:00
        assertThat(now.getOffset()).isEqualTo(ZoneOffset.ofHours(9));
        assertThat(now.getOffset().toString()).isEqualTo("+09:00");

        assertThatThrownBy(() -> {
            DateTimeFormatter.ofPattern("z|Z|x|X").format(now);
        }).isInstanceOf(DateTimeException.class);

        String formatted = DateTimeFormatter.ofPattern("Z|x|X").format(now);
        assertThat(formatted).isEqualTo("+0900|+09|+09");

        log.debug("now2: {}", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").format(now)); // 2023-10-18T17:41:27+0900
    }
}
