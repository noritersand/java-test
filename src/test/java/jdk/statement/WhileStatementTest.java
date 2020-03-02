package jdk.statement;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * while문 테스트 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class WhileStatementTest {
	private static final Logger logger = LoggerFactory.getLogger(WhileStatementTest.class);

	@Test
	public void testWhile() {
		int n = 0;
		while (n != 2) { // condition이 true면 반복함
			++n;
			logger.debug("{}", n);
		}
		Assert.assertEquals(2, n);
	}
}
