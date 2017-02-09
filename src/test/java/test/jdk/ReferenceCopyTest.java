package test.jdk;
import org.junit.Assert;
import org.junit.Test;

public class ReferenceCopyTest {
	@Test
	public void test1() {
		String str = "a";
		doSomething(str);
		Assert.assertNotEquals("b", str);

		int num = 1;
		doSomething(num);
		Assert.assertNotEquals(2, num);
		
		Integer number = 1;
		doSomething(number);
		Assert.assertNotEquals(new Integer(2), number);
		
		Vo vo = new Vo();
		vo.setStr("a");
		vo.setNum(1);
		doSomething(vo);
		Assert.assertEquals("b", vo.getStr());
		Assert.assertEquals(2, vo.getNum());
	}

	private void doSomething(Vo vo) {
		vo.setStr("b");
		vo.setNum(2);
	}

	private void doSomething(String obj) {
		obj = "b"; // obj = new String("b")
	}

	private void doSomething(int obj) {
		obj = 2;
	}

	private void doSomething(Integer obj) {
		obj = 2; // new Integer(2);
	}

}

class Vo {
	private String str;
	private int num;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}