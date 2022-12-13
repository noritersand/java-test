package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ErrorTest {

    @Test
    public void test() {
        try {
            throw new Error();
        } catch (Throwable e) {
            log.debug("하이");
        }
    }
}
