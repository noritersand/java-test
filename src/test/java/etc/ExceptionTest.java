package etc;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
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
			Assert.assertNull(e.getMessage());
		}
	}
}
