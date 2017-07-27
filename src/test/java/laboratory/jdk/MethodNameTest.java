package laboratory.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class MethodNameTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodNameTest.class);

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
	public void test1() {
		logger.debug("methodName() = " + methodName());
		logger.debug("CLIENT_CODE_STACK_INDEX = " + CLIENT_CODE_STACK_INDEX);
	}

	@Test
	public void test2() {
		String className = new Object() {}.getClass().getEnclosingClass().getName();
		logger.debug("className: " + className);
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		logger.debug("methodName: " + methodName);
	}
}
