package laboratory.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OperatorTest.class);
	
	@Test
	public void testTernary() {
		String first = "1";
		String second = "2";
		String third = "3";
		String fourth = "";

		String result = (!fourth.isEmpty()) ? fourth : (!third.isEmpty()) ? third : (!second.isEmpty()) ? second : first;
		Assert.assertEquals("3", result);
	}
	
	@Test
	public void testUnary() {
		int a = 0;
		Assert.assertEquals(1, ++a); // 변수 반환 이전에 연산. 따라서  +1의 결과인 1
		Assert.assertEquals(1, a);
		Assert.assertEquals(1, a++); // 변수 반환 이후에 연산. 따라서 +1 하기 전의 결과인 1
		Assert.assertEquals(2, a);
	}
}
