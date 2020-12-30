package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * break문 테스트
 * 
 * @since 2020-04-03
 * @author noritersand
 */
public class BreakTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BreakTest.class);

	private static final boolean T = true;
	private static final boolean F = false;
	
	@Test
	public void withLabeledIfStatement() {
		int n = 10;
		hello: if (n > 0) {
		    Assert.assertTrue(T);
		    if (n == 10) {
		    	Assert.assertTrue(T);
		        break hello; // hello if를 탈출
		    }
		    Assert.assertTrue(F); // 실행되지 않음
		}
	}
	
	@Test
	public void withLabeledBlock() {
		char c = 'A';
		yoohoo: {
		    if (c == 'A') {
		    	Assert.assertTrue(T);
		        break yoohoo; // yoohoo 블록을 탈출
		    }
		    Assert.assertTrue(F); // 실행되지 않음
		}
	}
}
