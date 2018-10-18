package jdk.statements;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * try-catch-finally 테스트 유닛
 * 
 * @since 2018-02-01
 * @author fixalot
 */
public class TryCatchFinallyTest {
	private static final Logger logger = LoggerFactory.getLogger(TryCatchFinallyTest.class);

	@Test
	public void withOutCatchStatement() {
		try {
			try {
				@SuppressWarnings("unused")
				int nan = 1 / 0; // 여기서 발생한 예외는 가장 바깥의 catch에서 받음
			} finally {
				// catch문이 없으면 finally라도 있어야 컴파일 에러 안남
				logger.debug("You know nothing John snow!");
			}			
		} catch (ArithmeticException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void finallyTest() {
		String str = "";
		try {
			str += "try";
		} finally {
			str += " finally";
		}
		Assert.assertEquals("try finally", str);
	}

	@Test
	public void finallyTest2() {
		String str = "";
		try {
			str += "try";
			throw new IllegalAccessError();
		} catch (IllegalAccessError e) {
			str += " catch";
		} finally {
			str += " finally";
		}
		Assert.assertEquals("try catch finally", str);
	}

	/**
	 * catch 에서 return 해봐야 finally 때문에 무시됨.
	 * 이런 이유때문에 finally에서 return을 하면 경고가 나타난다. 
	 * 
	 * @author fixalot
	 */
	@Test
	public void finallyTest3() {
		String str = weirdStatement();
		Assert.assertEquals("try catch finally", str);
	}

	@SuppressWarnings("finally")
	private String weirdStatement() {
		String str = "";
		try {
			str += "try";
			throw new IllegalAccessError();
		} catch (IllegalAccessError e) {
			str += " catch";
			logger.debug(str);
			return str;
		} finally {
			str += " finally";
			logger.debug(str);
			return str; // finally block does not complete normally
		}
	}
}
