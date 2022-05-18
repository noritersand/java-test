package jdk.java.lang;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 숫자 포맷 예외 테스트
 * 
 * @since 2017-09-08
 * @author fixalot
 */
public class NumberFormatExceptionTest {
	private static final Logger logger = LoggerFactory.getLogger(NumberFormatExceptionTest.class);

	@Test
	public void test() {
		try {
			String s = "FOOBAR";
			int i = Integer.parseInt(s);
			logger.debug("int value = " + i); // this line of code will never be reached
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
