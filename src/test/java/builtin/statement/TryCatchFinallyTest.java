package builtin.statement;

import testbed.exception.serverfault.InappropriateArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * try-catch-finally 테스트
 *
 * @author fixalot
 * @see misc.ExceptionTest
 * @since 2018-02-01
 */
@Slf4j
class TryCatchFinallyTest {

    /**
     * 새로 나온 try-with-resources statement<br>
     * catch나 finally 없어도 컴파일 에러 발생하지 않음<br>
     * 괄호 안에 선언되는 타입은 반드시 java.lang.AutoCloseable의 구현체여야 함(The resource type File does not implement java.lang.AutoCloseable).
     *
     * @throws IOException
     * @author noritersand
     */
    @Test
    void tryWithResources() throws IOException {
        try (Writer output = null) {
            assertTrue(1 != 2);
        }
    }

    /**
     * catch 작성 순서는 매우 중요하며, 아무렇게나 작성하면 컴파일 에러가 발생할 수 있다.<br>
     * 아래의 경우 catch 우선순위는 RuntimeException, Exception, InappropriateArgumentException(순이다. 즉, catch 우선순위는 작성 순서를 따른다.<br>
     * 그리고 try에서 InappropriateArgumentException(을 throw 하더라도  'Exception' catch에서 흡수해버리므로(부모를 잡으라고 작성하면 자식도 잡힘)<br>
     * 'InappropriateArgumentException(' catch는 dead code가 된다.
     *
     * @author noritersand
     */
    @Test
    void catchOrder() {
        try {
            int a = 0;
            if (0 == a) {
                throw new InappropriateArgumentException();
            }
        } catch (RuntimeException e) {
            // do nothing
        } catch (Exception e) {
            // do nothing
//		} catch (InappropriateArgumentException(e) { // 이 코멘트를 풀면 컴파일 에러 발생함:
//			 Unreachable catch block for InappropriateArgumentException(. It is already handled by the catch block for Exception
        }
    }

    @Test
    void withOutCatchStatement() {
        try {
            try {
                @SuppressWarnings("unused")
                int nan = 1 / 0; // 여기서 발생한 예외는 가장 바깥의 catch에서 받음
            } finally {
                // catch가 없으면 finally라도 있어야 컴파일 에러 안남
                log.debug("You know nothing John snow!");
            }
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    void finallyTest() {
        String str = "";
        try {
            str += "try";
        } finally {
            str += " finally";
        }
        assertEquals("try finally", str);
    }

    @Test
    void finallyTest2() {
        String str = "";
        try {
            str += "try";
            throw new RuntimeException();
        } catch (RuntimeException e) {
            str += " catch";
        } finally {
            str += " finally";
        }
        assertEquals("try catch finally", str);
    }

    @Test
    void finallyTest3() {
        String str = weirdStatement();
        assertEquals("weirdStatement", str);

        try {
            str = weirdStatement2();
        } catch (Exception e) {
            str = "Hello world!"; // catch의 throw는 finally가 있어 무시되었기 때문에 이 라인은 dead code가 됨.
        }
        assertEquals("weirdStatement2", str);
    }

    /**
     * catch의 return은 finally의 return에 의해 무시됨.<br>
     * 이 코드처럼 catch의 return/throw가 있을 때 finally에서 return을 명시하면<br>
     * 'finally block does not complete normally' 경고 발생함.
     *
     * @return
     * @author noritersand
     */
    @SuppressWarnings("finally")
    private String weirdStatement() {
        String str = "";
        try {
            str += "weird";
            throw new RuntimeException();
        } catch (RuntimeException e) {
            str += "State";
            log.debug(str);
            return "";
        } finally {
            str += "ment";
            log.debug(str);
            return str; // finally block does not complete normally
        }
    }

    /**
     * catch의 return은 finally의 return에 의해 무시됨.<br>
     * 이 코드처럼 catch의 return/throw가 있을 때 finally에서 return을 명시하면<br>
     * 'finally block does not complete normally' 경고 발생함.
     *
     * @return
     * @author noritersand
     */
    @SuppressWarnings("finally")
    private String weirdStatement2() {
        String str = "";
        try {
            str += "weird";
            throw new RuntimeException();
        } catch (RuntimeException e) {
            str += "State";
            log.debug(str);
            throw e;
        } finally {
            str += "ment2";
            log.debug(str);
            return str; // finally block does not complete normally
        }
    }
}
