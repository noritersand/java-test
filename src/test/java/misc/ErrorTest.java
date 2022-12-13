package misc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class ErrorTest {
    private static final Logger logger = LoggerFactory.getLogger(ErrorTest.class);

    @Test
    public void test() {
        try {
            throw new Error();
        } catch (Throwable e) {
            logger.debug("하이");
        }
    }
}
