package jdk.statement;

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
public class SwitchTest {
	private static final Logger logger = LoggerFactory.getLogger(SwitchTest.class);

	@Test
	public void howVariableInitialize() {
		int a = 2;
		switch (a) {
		case 1:
			int b = 0; // 여기는 오지 않는걸로 보이지만
			logger.debug(String.valueOf(b));
		case 2:
			b = 3; // 컴파일하면 초기화를 해주는걸로 바뀌나보다
			logger.debug(String.valueOf(b));
			Assert.assertEquals(3, b);
		}
	}
}
