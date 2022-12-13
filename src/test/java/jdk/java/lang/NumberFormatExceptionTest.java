package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 숫자 포맷 예외 테스트
 *
 * @author fixalot
 * @since 2017-09-08
 */
@Slf4j
public class NumberFormatExceptionTest {

    @Test
    public void test() {
        try {
            String s = "FOOBAR";
            int i = Integer.parseInt(s);
            log.debug("int value = {}", i); // this line of code will never be reached
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
        }
    }
}
