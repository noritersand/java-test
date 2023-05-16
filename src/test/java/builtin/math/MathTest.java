package builtin.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2018-01-23
 */
@Slf4j
public class MathTest {

    @Test
    void test() {
        log.debug("{}", Math.random());
    }

    @Test
    void testAbs() {
        assertEquals(123, 123);
        assertEquals(123, 123);
        assertEquals(0.1, 0.1);
        assertEquals(0.456, 0.456);
    }
}
