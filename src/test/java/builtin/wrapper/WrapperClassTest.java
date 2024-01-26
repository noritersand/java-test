package builtin.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class WrapperClassTest {

    @Test
    void copy() {
        Integer a = Integer.valueOf(123);
        Integer b = a;
        assertEquals(123, b.intValue());
        b = 456; // b = new Integer(456); String과 마찬가지로 새 객체가 할당된다.
        assertEquals(123, a.intValue());
        assertEquals(456, b.intValue());
    }

    @Test
    void testEquals() {
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
        // -128에서 127까지는 동등 연산자가 인스턴스 참조값을 비교하지 않음.
        // 이 범위의 값은 상수로 등록해서 사용한다고 함
        // 참고로 -128부터 127까지 표현 가능한 것은 1바이트임 : 2^7 = 128
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
    void testPassedByValue() {
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
    void testNullSafe() {
        Object a = null;
        String txt = (String) a;
        assertNull(txt);
    }

    /**
     * .valueOf() 메서드 테스트
     */
    @Test
    void testLongValueOf() {
        // null은 valueOf() 불가
        assertThrows(NumberFormatException.class, () -> {
            String nullValue = null;
            assertEquals(0, Long.valueOf(nullValue));
        });

        // empty string도 불가
        assertThrows(NumberFormatException.class, () -> {
            String blank = "";
            assertEquals(0, Long.valueOf(blank));
        });
        // 대신 apache NumberUtils를 쓰라고 하는데, 그건 0으로 반환함.
    }

    /**
     * null을 (오토언박싱이든 아니든) 원시타입으로 형 변환 하려고 하면 null-safe하지 않음.
     *
     * @author fixalot
     */
    @Test
    void testNullSafe2() {
        assertThrows(NullPointerException.class, () -> {
            Object chuckNorris = null;
            int illBeNull = (Integer) chuckNorris;
        });

        assertThrows(NullPointerException.class, () -> {
            Integer chucknorris = null;
            int illBeNull = chucknorris;
        });
    }

    @Test
    void checkZero() {
        assertEquals(Long.valueOf(0L), Long.valueOf(0L));
    }
}
