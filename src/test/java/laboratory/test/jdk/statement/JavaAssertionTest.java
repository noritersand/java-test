package laboratory.test.jdk.statement;

import org.junit.Test;

public class JavaAssertionTest {
	public static void main(String[] args) {
		int i = -1;
		assert i > 0;
	}

	@Test
	public void testAssertion() {

		/*
		 * assert 비교연산식 : "좌변이 틀렸을 때 던질 AssertionError의 detailMessage";
		 */

		int a = 1;
		int b = 2;

		assert a != b;

		int i = -1;
		assert i < 0;

		String pageNo = "abc";
		assert (!pageNo.equals(""));

		int age = 99;
		assert age > 0 : "나이는 음수가 될 수 없습니다:" + age;
	}
}
