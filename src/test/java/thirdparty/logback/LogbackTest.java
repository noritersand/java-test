package thirdparty.logback;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class LogbackTest {
    private static final Logger log = LoggerFactory.getLogger(LogbackTest.class);

    public static void main(String[] args) {
        log.debug("running java application");
    }

    @Test
    public void loggingWithJunit() {
        Integer a = Integer.valueOf("123");
        log.debug("{}", a);

        log.debug("unit testing");
        log.debug("unit", "testing"); // "testing"은 안찍힘
    }

    @Test
    public void loggingWithFormat() {
        log.debug("{} {} {} {}", "이거", "저거", "나는 누구", "여긴 어디");
        log.debug("{} {}", new Object[]{"hello there!", "if you ask, im waldo"});
//		log.debug("im {}", null); // 이렇게는 안됨
        log.debug("im {}", "null");
    }

    @Test
    public void loggingError() {
        try {
            throw new RuntimeException("1234");
        } catch (Exception e) {
            log.error("exceptionName: {}, message: {}", e.getClass().getName(), e.getMessage());
            log.error(e.getMessage(), e);
        }
    }
}
