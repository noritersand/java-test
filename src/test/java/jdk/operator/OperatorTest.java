package jdk.operator;

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
public class OperatorTest {
	private static final Logger logger = LoggerFactory.getLogger(OperatorTest.class);

	@Test
	public void negativeSign() {
		Assert.assertTrue(-3 < 0);
		final int three = 3;
		Assert.assertEquals(-3, -(three));
		Assert.assertEquals(-3, -three);
		Assert.assertEquals(3, -(-three));
		Assert.assertEquals(-3, -(-(-three)));
	}
	
	@Test
	public void ternaryOperator() {
		String first = "1";
		String second = "2";
		String third = "3";
		String fourth = "";

		String result = (!fourth.isEmpty()) ? fourth : (!third.isEmpty()) ? third : (!second.isEmpty()) ? second : first;
		Assert.assertEquals("3", result);
	}

	@Test
	public void unaryOperator() {
		int a = 0;
		Assert.assertEquals(1, ++a); // 변수 반환 이전에 연산. 따라서 +1의 결과인 1
		Assert.assertEquals(1, a);
		Assert.assertEquals(1, a++); // 변수 반환 이후에 연산. 따라서 +1 하기 전의 결과인 1
		Assert.assertEquals(2, a);

		// 반복문에서 증감연산자는 피연산자의 어느쪽에 있어도 상관 없다.
		for (int i = 0; i < 2; ++i) {
			logger.debug(String.valueOf(i));
		}
		for (int i = 0; i < 2; i++) {
			logger.debug(String.valueOf(i));
		}
	}
	
	@Test
	public void binaryOperator() {
		Assert.assertEquals(2, 1 + 1);
	}
	
	@Test
	public void modulusOperator() {
		Assert.assertEquals(0, 0 % 3);
		Assert.assertEquals(1, 1 % 3);
		Assert.assertEquals(2, 2 % 3);
		Assert.assertEquals(0, 3 % 3);
		Assert.assertEquals(1, 4 % 3);
		Assert.assertEquals(2, 5 % 3);
		Assert.assertEquals(0, 6 % 3);
	}
	
}
