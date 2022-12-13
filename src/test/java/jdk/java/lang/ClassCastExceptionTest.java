package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 형변환 실패 예외 테스트
 *
 * @author fixalot
 * @since 2017-09-08
 */
@Slf4j
public class ClassCastExceptionTest {

    @Test
    public void case1() {
        Object nan = "i'm not number";
        try {
            Integer number = (Integer) nan; // should be exception
            log.debug(String.valueOf(number));
        } catch (ClassCastException e) {
            log.error(e.getMessage(), e);
        }
    }
}
