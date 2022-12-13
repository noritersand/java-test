package jdk.statement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class WrapperClassTest {
    private static final Logger logger = LoggerFactory.getLogger(WrapperClassTest.class);

    @Test
    public void copy() {
        final Integer a = Integer.valueOf(123);
        Integer b = a;
        assertEquals(123, b.intValue());
        b = 456; // b = new Integer(456); String과 마찬가지로 새 객체가 할당된다.
        assertEquals(123, a.intValue());
        assertEquals(456, b.intValue());
    }

    @Test
    public void testEquals() {
        Long a = Long.valueOf(12345);
        Long b = Long.valueOf(12345);
        assertFalse(a == b); // wrapper 타입은 동등 연산자에서 객체 아이디를 비교하지 값을 비교하지 않음. **반만 맞는 설명임. 아래를 볼 것**
        assertTrue(a.equals(b)); // correct


        // 2022-06-08 어마어마한 것을 발견함
        assertTrue(Long.valueOf(-129) != Long.valueOf(-129));
        assertTrue(Long.valueOf(-128) == Long.valueOf(-128));
        assertTrue(Long.valueOf(0) == Long.valueOf(0));
        assertTrue(Long.valueOf(1L) == Long.valueOf(1L));
        assertTrue(Long.valueOf(20L) == Long.valueOf(20L));
        assertTrue(Long.valueOf(100L) == Long.valueOf(100L));
        assertTrue(Long.valueOf(126L) == Long.valueOf(126L));
        assertTrue(Long.valueOf(127L) == Long.valueOf(127L));
        // 여기까지는 동등 연산자에서 객체 아이디를 비교하지 않음. 127L 까지는 1바이트라서 정수로 변환된다고 함(자바니께 원시타입으로 변환되는거겠지)
        // 1바이트는 -128부터 127까지 표현 가능 (2^7 = 128)
        assertTrue(Long.valueOf(128L) != Long.valueOf(128L));
        assertTrue(Long.valueOf(129L) != Long.valueOf(129L));
        assertTrue(Long.valueOf(130L) != Long.valueOf(130L));
        assertTrue(Long.valueOf(12345L) != Long.valueOf(12345L));
        assertTrue(Long.valueOf(65536L) != Long.valueOf(65536L));
        assertTrue(Long.valueOf(4294967296L) != Long.valueOf(4294967296L));

        assertTrue(Integer.valueOf(127) == Integer.valueOf(127));
        assertTrue(Integer.valueOf(128) != Integer.valueOf(128));
    }

    @Test
    public void testPassedByValue() {
        Integer num = 456;
        changeValue(num); // 내부에서 123으로 변경
        // 그래도 num은 456으로 유지된다. wrapper 타입이 일반적인 reference 타입과 다른 특징임.
        assertEquals(Integer.valueOf(456), num);
    }

    private void changeValue(Integer num) {
        num = 123; // 이 코드는 num = new Integer(123) 과 같다.
    }

    /**
     * null을 명시적으로 형변환하는 것은 null-safe함
     *
     * @author fixalot
     */
    @Test
    public void testNullSafe() {
        Object a = null;
        String txt = (String) a;
        assertNull(txt);
    }

    /**
     * .valueOf() 메서드 테스트
     */
    @Test
    public void testLongValueOf() {
        // null은 valueOf() 불가
        try {
            String nullValue = null;
            assertEquals(0, Long.valueOf(nullValue));
        } catch (NumberFormatException e) {
            logger.debug("{}", "java.lang.NumberFormatException: Cannot parse null string");
        }
        // empty string도 불가
        try {
            String blank = "";
            assertEquals(0, Long.valueOf(blank));
        } catch (NumberFormatException e) {
            logger.debug("{}", "java.lang.NumberFormatException: For input string: \"\"");
        }
        // 대신 apache NumberUtils를 쓰라고 하는데, 그건 0으로 반환함.
    }

    /**
     * null을 (오토언박싱이든 아니든) 원시타입으로 형 변환 하려고 하면 null-safe하지 않음.
     *
     * @author fixalot
     */
    @Test
    public void testNullSafe2() {
        try {
            Object chuckNorris = null;
            @SuppressWarnings({"unused", "null"})
            int imgonnabenull = (Integer) chuckNorris;
        } catch (Exception e) {
            logger.debug("예외 발생#1: {}", e.getLocalizedMessage());
        }

        try {
            Integer chucknorris = null;
            @SuppressWarnings({"unused", "null"})
            int imgonnabenull = chucknorris;
        } catch (Exception e) {
            logger.debug("예외 발생#2: {}", e.getLocalizedMessage());
        }
    }

    @Test
    public void checkZero() {
        assertTrue(Long.valueOf(0L).equals(Long.valueOf(0L)));
    }
}
