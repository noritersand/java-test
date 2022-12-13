package misc;

import jdk.statement.TryCatchFinallyTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 예외 테스트
 *
 * @author fixalot
 * @see TryCatchFinallyTest
 * @since 2017-07-27
 */
public class ExceptionTest {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    @Test
    public void logging() {
        try {
            throw new RuntimeException("for test");
        } catch (Exception e) {
            logger.debug("I'll catch you so much!");
        }
    }

    /**
     * throws는 언제 선언해야 하는가?
     *
     * @throws Exception
     * @author fixalot
     */
//	@Test
    public void needThrowsKeyword() throws Exception {
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
    public void doNotNeedThrows() {
        throw new RuntimeException("hello");
    }

    /**
     * RuntimeException 계열이 아니면 throws 명시해야 함.
     *
     * @throws IOException
     * @author fixalot
     */
//	@Test
    public void needThrowsKeyword2() throws IOException {
        throw new IOException("hello");
    }

    @Test
    public void shouldBeNull() {
        /*
         * 에러 메시지를 설정하지 않으면 에러 메시지가 null로 찍힘.
         * NullPointerException과는 관계 없으나 혼동할 가능성이 있다.
         *
         * logger.error(e.getMessage(), e);
         * ->
         * [ERROR] shouldBeNull: null
         * java.lang.RuntimeException: null
         * 			at etc.ExceptionTest.shouldBeNull(ExceptionTest.java:31)
         */
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            assertNull(e.getMessage());
        }
    }
}
