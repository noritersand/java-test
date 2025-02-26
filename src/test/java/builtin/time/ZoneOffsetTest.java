package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ZoneOffset 클래스 테스트
 */
@Slf4j
public class ZoneOffsetTest {

    /**
     * <p>{@link ZoneOffset}: UTC와의 차이를 시간 오프셋(+-09:00 형식)으로 표현함</p>
     */
    @Test
    void basicUsages() {
        assertThat(ZoneOffset.UTC).isEqualTo(ZoneOffset.of("Z"));
        assertThat(ZoneOffset.MAX).isEqualTo(ZoneOffset.of("+18:00"));
        assertThat(ZoneOffset.MIN).isEqualTo(ZoneOffset.of("-18:00"));

        ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
        assertThat(zoneOffset.toString()).isEqualTo("+09:00");

//        for (String zoneId : ZoneOffset.getAvailableZoneIds()) {
//            log.debug("zoneId: {}", zoneId);
//        }

        assertThat(ZoneOffset.ofHours(9)).isEqualTo(ZoneOffset.of("+09:00"));
        assertThat(ZoneOffset.ofHoursMinutes(9, 20)).isEqualTo(ZoneOffset.of("+09:20"));
        assertThat(ZoneOffset.ofHoursMinutesSeconds(9, 20, 30)).isEqualTo(ZoneOffset.of("+09:20:30"));
    }
}
