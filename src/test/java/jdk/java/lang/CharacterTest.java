package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class CharacterTest {

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void compare() {
        char a = 'a';
        char b = 'b';
        Character aa = 'a';
        Character bb = 'b';

        // 동등 비교
        assertEquals('a', a);
        assertNotEquals(a, b);
        assertEquals(a, (char) aa);
        assertEquals((char) bb, b);

        // String과 비교
        assertNotEquals("a", 'a'); // equals()로는 다른 타입끼리 비교 불가
        assertEquals('a', a);

        // 새로 만든 인스턴스와 비교
        Character inst = Character.valueOf('a');
        assertEquals(a, (char) inst); // Character.equals()에서 래퍼타입이 갖고 있는 value로 비교함.
        assertSame(aa, inst); // 이것도 마찬가지. 래퍼타입이 갖고 있는 value로 비교됨. JDK9부터 되는걸로 추정
        assertNotEquals("a", inst); // equals()로는 다른 타입끼리 비교 불가
        assertEquals('a', (char) inst);
        assertEquals('a', (char) inst);
    }

    @Test
    @SuppressWarnings({"null", "unused"})
    public void shouldBeError() {
        assertThrows(NullPointerException.class, () -> {
            Character e = null;
            boolean equal = 'b' == e;
        });
        assertThrows(NullPointerException.class, () -> {
            Integer i = null;
            boolean equal = 1 == i;
        });
    }

    @Test
    void getASCIICode() {
        final String binary = "5";
        final String alphabet = "a";
        final String korean = "한";
        log.debug("{}", (int) binary.charAt(0));
        log.debug("{}", (int) alphabet.charAt(0));
        log.debug("{}", (int) korean.charAt(0));
    }

    @Test
    void fromASCIICode() {
        char a = 65;
        char b = 98;
        assertEquals('A', a);
        assertEquals('b', b);
    }
}
