package thirdparty.apache.commons;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class StopWatchTest {
    //	@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(StopWatchTest.class);

    /**
     * usage
     *
     * @author fixalot
     */
    @Test
    public void test() {
        StopWatch watch = new StopWatch();
        doUselessThing();
        assertEquals(0, watch.getNanoTime()); // 시작하기 전에는 0

        watch.start(); // 시작
        assertTrue(watch.isStarted());

        doUselessThing();
        logger.debug("경과 시간(나노초): {}", String.valueOf(watch.getNanoTime()));

        watch.suspend(); // 일시정지
        assertTrue(watch.isSuspended());

        watch.resume(); // 재시작
        assertTrue(watch.isStarted());

        watch.split(); // 임시 기록
        logger.debug("임시 기록 시간(나노초): {}", String.valueOf(watch.getSplitNanoTime()));

        logger.debug("시작된 시간(나노초): {}", String.valueOf(watch.getStartTime()));

        watch.stop(); // 중단
        watch.reset(); // 초기화

        assertTrue(watch.isStopped());
    }

    private long doUselessThing() {
        long sum = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
