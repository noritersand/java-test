package jdk.java.lang;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 형변환 실패 예외 테스트
 *
 * @since 2017-09-08
 * @author fixalot
 */
public class ClassCastExceptionTest {
	private static final Logger logger = LoggerFactory.getLogger(ClassCastExceptionTest.class);
	
	@Test
	public void case1() {
		Object nan = "i'm not number";
		try {
			Integer number = (Integer) nan; // should be exception
			logger.debug(String.valueOf(number));
		} catch (ClassCastException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
