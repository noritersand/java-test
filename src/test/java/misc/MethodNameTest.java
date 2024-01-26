package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class MethodNameTest {

    private static final int CLIENT_CODE_STACK_INDEX;

    static {
        // Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5 and 1.6
        int i = 0;
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            i++;
            if (ste.getClassName().equals(MethodNameTest.class.getName())) {
                break;
            }
        }
        CLIENT_CODE_STACK_INDEX = i;
    }

    public static String methodName() {
        return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX].getMethodName();
    }

    public static void main(String[] args) {
        System.out.println("methodName() = " + methodName());
        System.out.println("CLIENT_CODE_STACK_INDEX = " + CLIENT_CODE_STACK_INDEX);
    }

    @Test
    void test1() {
        log.debug("methodName() = {}", methodName());
        log.debug("CLIENT_CODE_STACK_INDEX = {}", CLIENT_CODE_STACK_INDEX);
    }

    @Test
    void test2() {
        String className = new Object() {
        }.getClass().getEnclosingClass().getName();
        log.debug("className: {}", className);
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.debug("methodName: {}", methodName);
    }
}
