package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
    public void shouldBeError() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            String s = "FOOBAR";
            int i = Integer.parseInt(s);
            log.debug("int value = {}", i); // this line of code will never be reached
        });
    }
}
