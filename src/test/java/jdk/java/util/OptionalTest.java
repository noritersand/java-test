package jdk.java.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * java.util.Optional 테스트 슈ㅌ
 */
public class OptionalTest {
    private static final Logger logger = LoggerFactory.getLogger(OptionalTest.class);

    @Test
    public void testEmpty() {
        Optional<Object> empty = Optional.empty();
        assertNotNull(empty);
    }

    @Test
    public void testOf() {
        Optional<String> txt = Optional.of("1234");
        assertEquals("1234", txt.get());
        ;
        assertTrue(txt.isPresent());
        assertFalse(txt.isEmpty());
        assertEquals("Optional[1234]", txt.toString());
        assertTrue(txt.equals(Optional.of("1234")));
    }
}
