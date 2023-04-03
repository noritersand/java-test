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
    
    @Test
    public void testIfPresent() {
        String str = "qwer";
        Optional<String> so = Optional.of(str);
        so.ifPresent(s -> {
            log.debug("{}", "It is presented.");
        });
    }

    /**
     * 이렇게 쓰는 건가...?
     */
    @Test
    public void testIfPresentOrElse() {
        String str = null;
        Optional<String> so = Optional.ofNullable(str);
        so.ifPresentOrElse(s -> {
            log.debug("{}", "It is presented.");
        }, () -> {
            log.debug("{}", "It is not presented.");
        });
    }
}
