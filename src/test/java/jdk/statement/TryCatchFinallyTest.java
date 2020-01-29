package jdk.statement;

import java.io.IOException;
import java.io.Writer;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import misc.ExceptionTest;

/**
 * try-catch-finally 테스트 케이스
 * 
 * @since 2018-02-01
 * @author fixalot
 * @see ExceptionTest
 */
public class TryCatchFinallyTest {
	private static final Logger logger = LoggerFactory.getLogger(TryCatchFinallyTest.class);

	
	/**
	 * 새로 나온 try-with-resources statement<br>
	 * catch나 finally 없어도 컴파일 에러 발생하지 않음<br>
	 * 괄호 안에 선언되는 타입은 반드시 java.lang.AutoCloseable의 구현체여야 함(The resource type File does not implement java.lang.AutoCloseable).
	 * 
	 * @throws IOException
	 * @author noritersand
	 */
	@Test
	public void tryWithResources() throws IOException {
		try (Writer output = null) {
			Assert.assertTrue(1 != 2);
		}
	}
	
	/**
	 * catch 작성 순서는 매우 중요하며, 아무렇게나 작성하면 컴파일 에러가 발생할 수 있다.<br>
	 * 아래의 경우 catch 우선순위는 RuntimeException, Exception, IllegalArgumentException순이다. 즉, catch 우선순위는 작성 순서를 따른다.<br>
	 * 그리고 try에서 IllegalArgumentException을 throw 하더라도  'Exception' catch에서 흡수해버리므로(부모를 잡으라고 작성하면 자식도 잡힘)<br>
	 * 'IllegalArgumentException' catch는 dead code가 된다. 
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
				// catch가 없으면 finally라도 있어야 컴파일 에러 안남
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
			str = "Hello world!"; // catch의 throw는 finally가 있어 무시되었기 때문에 이 라인은 dead code가 됨.
		}
		Assert.assertEquals("weirdStatement2", str);
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
			throw new IllegalAccessError();
		} catch (IllegalAccessError e) {
			str += "State";
			logger.debug(str);
			return "";
		} finally {
			str += "ment";
			logger.debug(str);
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
