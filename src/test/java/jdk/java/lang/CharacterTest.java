package jdk.java.lang;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class CharacterTest {
    //	@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CharacterTest.class);

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void compare() {
        char a = 'a';
        char b = 'b';
        Character aa = 'a';
        Character bb = 'b';

        // 동등 비교
        assertTrue(a == 'a');
        assertFalse(a == b);
        assertTrue(a == aa);
        assertTrue(bb.equals(b));

        // String과 비교
        assertFalse("a".equals('a')); // equals()로는 다른 타입끼리 비교 불가
        assertTrue(a == "a".charAt(0));

        // 새로 만든 인스턴스와 비교
        Character inst = Character.valueOf('a');
        assertTrue(a == inst); // Character.equals()에서 래퍼타입이 갖고 있는 value로 비교함.
        assertTrue(aa == inst); // 이것도 마찬가지. 래퍼타입이 갖고 있는 value로 비교됨. JDK9부터 되는걸로 추정
        assertFalse(inst.equals("a")); // equals()로는 다른 타입끼리 비교 불가
        assertTrue(inst.equals("a".charAt(0)));
        assertTrue(inst == "a".charAt(0));
    }

    @Test
    @SuppressWarnings({"null", "unused"})
    public void compareWithNull() {
        try {
            Character e = null;
            boolean equal = e == 'b'; // NullPointerException

            Integer i = null;
            equal = i == 1; // NullPointerException
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void getASCIICode() {
        final String binary = "5";
        final String alphabet = "a";
        final String korean = "한";
        logger.debug("{}", (int) binary.charAt(0));
        logger.debug("{}", (int) alphabet.charAt(0));
        logger.debug("{}", (int) korean.charAt(0));
    }

    @Test
    public void fromASCIICode() {
        char a = 65;
        char b = 98;
        assertEquals('A', a);
        assertEquals('b', b);
    }
}
