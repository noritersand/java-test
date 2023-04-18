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
    
    /**
     * <p>Optional 인스턴스를 생성하는 메서드 of() 테스트</p>
     * <p>null이 주어지면 NPE 발생함</p>
     */
    @Test
    public void testOf() {
        Optional<String> op = Optional.of("1234");
        assertEquals("1234", op.get());
        assertEquals("Optional[1234]", op.toString());
        assertEquals(op, Optional.of("1234"));

        try {
            Optional<String> op2 = Optional.of(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    /**
     * <p>ofNullable()은 of()와 달리 NPE에 안전함</p>
     */
    @Test
    public void testOfNullable() {
        Optional<String> op = Optional.ofNullable("1234");
    }

    /**
     * <p>isPresent()는 Optional에 값이 있으면 true를 반환함</p>
     */
    @Test
    public void testIsPresent() {
        assertTrue(Optional.of("1234").isPresent());
        assertTrue(Optional.of("").isPresent()); // 빈 문자열인지는 판단하지 않음
        assertFalse(Optional.ofNullable(null).isPresent());
    }

    /**
     * <p>isEmpty()는 isPresent()와 반대로 Optional에 값이 비어있어야 true를 반환함</p>
     */
    @Test
    public void testIsEmpty() {
        assertTrue(Optional.ofNullable(null).isEmpty());
        assertFalse(Optional.of("1234").isEmpty());
        assertFalse(Optional.of("").isEmpty());
    }

    /**
     * <p>Optional의 값이 있으면 실행할 콜백 메서드</p>
     */
    @Test
    public void testIfPresent() {
        String str = "qwer";
        Optional<String> op = Optional.of(str);
        op.ifPresent(s -> {
            log.debug("{}", "It is presented.");
        });
    }

    /**
     * <p>값이 비어 있으면(null이면) 실행할 콜백 메서드까지 지정할 수 있음</p>>
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
