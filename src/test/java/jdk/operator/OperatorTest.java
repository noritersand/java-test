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

	/**
	 * 할당 연산자<sup>assignment operator</sup> 테스트
	 * 
	 * @author noritersand
	 */
	@Test
	public void assignmentOperator() {
		// 할당
		int n = 1;
		Assert.assertEquals(1, n);
		
		// 산술 연산 후 할당
		n += 2;
		Assert.assertEquals(3, n);
		n -= 1;
		Assert.assertEquals(2, n);
		n *= 5;
		Assert.assertEquals(10, n);
		n /= 2;
		Assert.assertEquals(5, n);
		
		// 변수 하나 이상을 한 번에 초기화
		int num = n = 9 + 1;
		Assert.assertEquals(10, num);
		Assert.assertEquals("20", String.valueOf(num = 20));
		
		// 좀 더 많이 해볼까?
		int a, b, c, d, e;
		a = b = c = d = e = 100; // 다섯개를 한 번에 조로록
		Assert.assertTrue(a == b && b == c && c == d && d == e && e == 100); // 당연히 다섯 변수의 값은 같음
	}
	
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
			logger.debug("{}", i);
		}
		for (int i = 0; i < 2; i++) {
			logger.debug("{}", i);
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
