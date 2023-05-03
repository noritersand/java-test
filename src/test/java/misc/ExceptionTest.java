package misc;

import jdk.statement.TryCatchFinallyTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 예외 테스트
 *
 * @author fixalot
 * @see TryCatchFinallyTest
 * @since 2017-07-27
 */
@Slf4j
public class ExceptionTest {

    @Test
    void logging() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("for test");
        });
    }

    /**
     * throws는 언제 선언해야 하는가?
     *
     * @throws Exception
     * @author fixalot
     */
//	@Test
    void needThrowsKeyword() throws Exception {
        throw new Exception("hello");
    }

    /**
     * RuntimeException은 throws 생략 가능.
     *
     * <blockquote>RuntimeException and its subclasses are uncheckedexceptions. Unchecked exceptions do not need to bedeclared in a method
     * or constructor's throws clause if theycan be thrown by the execution of the method or constructor andpropagate outside the method or
     * constructor boundary.</blockquote>
     *
     * @author fixalot
     */
//	@Test
    void doNotNeedThrows() {
        throw new RuntimeException("hello");
    }

    /**
     * RuntimeException 계열이 아니면 throws 명시해야 함.
     *
     * @throws IOException
     * @author fixalot
     */
//	@Test
    void needThrowsKeyword2() throws IOException {
        throw new IOException("hello");
    }

    @Test
    void shouldBeNull() {
        /*
         * 에러 메시지를 설정하지 않으면 에러 메시지가 null로 찍힘.
         * 사실 NullPointerException과는 관계 없는데, 혼동할 여지가 있다.
         *
         * log.error(e.getMessage(), e);
         * ->
         * [ERROR] shouldBeNull: null
         * java.lang.RuntimeException: null
         * 			at etc.ExceptionTest.shouldBeNull(ExceptionTest.java:31)
         */
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException();
        });
    }
}
