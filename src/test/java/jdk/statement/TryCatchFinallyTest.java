package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import misc.ExceptionTest;

/**
 * try-catch-finally 테스트 케이스
 * 
 * @see ExceptionTest
 * @since 2018-02-01
 * @author fixalot
 */
public class TryCatchFinallyTest {
	private static final Logger logger = LoggerFactory.getLogger(TryCatchFinallyTest.class);

	/**
	 * catch문 작성 순서는 매우 중요하며, 아무렇게나 작성하면 컴파일 에러가 발생할 수 있다.<br>
	 * 아래의 경우 catch 우선순위는 RuntimeException, Exception, IllegalArgumentException순이다. 즉, catch 우선순위는 작성 순서를 따른다.<br>
	 * 그리고 try문 바디에서 IllegalArgumentException을 throw 하더라도  'Exception' catch문에서 흡수해버리므로(부모를 잡으라고 작성하면 자식도 잡힘)<br>
	 * 'IllegalArgumentException' catch문은 있으나 마나한 코드가 되는 것이다. 
	 * 
	 * @author noritersand
	 */
	@Test
	public void catchOrder() {
		try {
			int a = 0;
			if (a == 0) {
				throw new IllegalArgumentException();
			}
		} catch (RuntimeException e) {
			// do nothing
		} catch (Exception e) {
			// do nothing
//		} catch (IllegalArgumentException e) { // 이 코멘트를 풀면 컴파일 에러 발생함: 
			// Unreachable catch block for IllegalArgumentException. It is already handled by the catch block for Exception
		}
	}

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
			Assert.assertEquals("/ by zero", e.getMessage());
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

	@Test
	public void finallyTest3() {
		String str = weirdStatement();
		Assert.assertEquals("weirdStatement", str);
		
		try {
			str = weirdStatement2();			
		} catch (Exception e) {
			str = "Hello world!"; // catch의 throw는 finally 때문에 무시되었기 때문에 이 라인은 dead code가 됨.
		}
		Assert.assertEquals("weirdStatement2", str);
	}

	/**
	 * catch에서 return 해봐야 finally 때문에 무시됨. 이런 이유때문에 finally에서 return을 하면 경고가 나타난다.
	 * 
	 * @return
	 * @author noritersand
	 */
	@SuppressWarnings("finally")
	private String weirdStatement() {
		String str = "";
		try {
			str += "weird";
			throw new IllegalAccessError();
		} catch (IllegalAccessError e) {
			str += "State";
			logger.debug(str);
			return str;
		} finally {
			str += "ment";
			logger.debug(str);
			return str; // finally block does not complete normally
		}
	}
	
	/**
	 * 얘도 마찬가지로 catch에서 throw 해봐야 finally 때문에 무시되됨
	 * 
	 * @return
	 * @author noritersand
	 */
	@SuppressWarnings("finally")
	private String weirdStatement2() {
		String str = "";
		try {
			str += "weird";
			throw new IllegalAccessError();
		} catch (IllegalAccessError e) {
			str += "State";
			logger.debug(str);
			throw e;
		} finally {
			str += "ment2";
			logger.debug(str);
			return str; // finally block does not complete normally
		}
	}
}
