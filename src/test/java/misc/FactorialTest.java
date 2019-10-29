package misc;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 팩토리얼 테스트 케이스
 * 
 * @since 2018-10-31
 * @author fixalot
 */
public class FactorialTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FactorialTest.class);

	@Test
	public void test() {
		// 4! = 4 x 3 x 2 x 1 = 24
		// 4! = 4 x 3 x 2! = 24
		// 4! = 4 x 3! = 24
		// 4! = 4! = 24

		Assert.assertEquals(1, factorial(1));
		Assert.assertEquals(2, factorial(2));
		Assert.assertEquals(6, factorial(3));
		Assert.assertEquals(24, factorial(4));
		Assert.assertEquals(120, factorial(5));
	}

	public int factorial(int n) {
		if (n > 1) {
			return n * factorial(n - 1); // 재귀호출
		} else {
			return 1;
		}
	}
}
