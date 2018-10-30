package jdk.statement;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java unlimited method arguments test case
 * 
 * @since 2018-04-03
 * @author fixalot
 */
public class MethodArgumentsTest {
	private static final Logger logger = LoggerFactory.getLogger(MethodArgumentsTest.class);

	public static void testMe(int... args) {
		for (int ele : args) {
			logger.debug(String.valueOf(ele));
		}
	}
	
	@Test
	public void test() {
		MethodArgumentsTest.testMe(1, 2, 3, 4, 5);
	}
}
