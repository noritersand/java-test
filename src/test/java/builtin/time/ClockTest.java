package builtin.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.ZoneId;

/**
 * Clock 클래스 테스트
 */
@Slf4j
public class ClockTest {
    private static final String ID_ASIA_SEOUL = "Asia/Seoul";
    private static final String ID_UTC = "UTC";

    /**
     * TODO Clock 타입에 대한 설명
     */
    @Test
    void basicUsages() {
        Clock system = Clock.system(ZoneId.of(ID_ASIA_SEOUL));
    }
}
