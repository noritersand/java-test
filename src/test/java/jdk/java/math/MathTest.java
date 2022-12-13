package jdk.java.math;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2018-01-23
 */
@Slf4j
public class MathTest {

    @Test
    public void test() {
        log.debug("{}", Math.random());
    }

    @Test
    public void testAbs() {
        assertEquals(123, Math.abs(-123));
        assertEquals(123, Math.abs(123));
        assertEquals(0.1, Math.abs(0.1));
        assertEquals(0.456, Math.abs(-0.456));
    }
}
