package jdk.java.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * java.util.Optional 테스트 슈ㅌ
 */
@Slf4j
public class OptionalTest {

    @Test
    public void testEmpty() {
        Optional<Object> empty = Optional.empty();
        assertNotNull(empty);
    }

    @Test
    public void testOf() {
        Optional<String> txt = Optional.of("1234");
        assertEquals("1234", txt.get());
        assertTrue(txt.isPresent());
        assertFalse(txt.isEmpty());
        assertEquals("Optional[1234]", txt.toString());
        assertEquals(txt, Optional.of("1234"));
    }
}
