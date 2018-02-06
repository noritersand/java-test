package jdk.statements;

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

	/**
	 * switch 안의 로컬 변수는 마치 자바스크립트의 끌어올림처럼 작동함.
	 * 
	 * @author fixalot
	 */
	@Test
	public void whenVariableInitialize() {
		int a = 2;
		switch (a) {
		case 1:
			int b = 0; // 여기는 오지 않는걸로 보이지만
			logger.debug(String.valueOf(b));
		case 2:
//			b; // 이렇게 하면 컴파일 에러: Syntax error, insert "VariableDeclarators" to complete LocalVariableDeclaration
			b = 3;
			logger.debug(String.valueOf(b));
			Assert.assertEquals(3, b);
		}
	}
}
