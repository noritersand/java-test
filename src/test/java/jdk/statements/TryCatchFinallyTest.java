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
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TryCatchFinallyTest.class);

	@Test
	public void finallyTest() {
		String str = "";
		try {
			str += "try";
		} finally {
			str += " ";
			str += "finally";
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
			str += " ";
			str += "catch";
		} finally {
			str += " ";
			str += "finally";
		}
		Assert.assertEquals("try catch finally", str);
	}
}
