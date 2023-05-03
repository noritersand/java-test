package thirdparty.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class LogbackTest {

    public static void main(String[] args) {
        log.debug("running java application");
    }

    @Test
    void loggingWithJunit() {
        Integer a = Integer.valueOf("123");
        log.debug("{}", a);

        log.debug("unit testing");
        log.debug("unit", "testing"); // "testing"은 안찍힘
    }

    @Test
    void loggingWithFormat() {
        log.debug("{} {} {} {}", "이거", "저거", "나는 누구", "여긴 어디");
        log.debug("{} {}", "hello there!", "if you ask, im waldo");
//		log.debug("im {}", null); // 이렇게는 안됨
        log.debug("im {}", "null");
    }

    @Test
    void loggingError() {
        try {
            throw new RuntimeException("1234");
        } catch (Exception e) {
            log.error("exceptionName: {}, message: {}", e.getClass().getName(), e.getMessage());
            log.error(e.getMessage(), e);
        }
    }
}
