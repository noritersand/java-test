package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ZoneId 클래스 테스트
 */
@Slf4j
public class ZoneIdTest {
    private static final String ID_ASIA_SEOUL = "Asia/Seoul";
    private static final String ID_UTC = "UTC";

    /**
     * <p>{@link ZoneId}: 시간대 식별자를 나타냄</p>
     * <p>서울의 Zone ID는 Asia/Seoul</p>
     */
    @Test
    void basicUsages() {
        // 사용 가능한 Zone ID 출력.
//        for (String zoneId : ZoneId.getAvailableZoneIds()) {
//            log.debug("zoneId: {}", zoneId);
//        }
        ZoneId zid = ZoneId.of(ID_ASIA_SEOUL);
        assertThat(zid).isNotNull();
        assertThat(zid.toString()).isEqualTo("Asia/Seoul");
    }
}
