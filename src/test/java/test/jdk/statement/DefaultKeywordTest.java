package test.jdk.statement;
import org.junit.Assert;
import org.junit.Test;

public class DefaultKeywordTest {
	@Test
	public void test1() {
		C c = new C();
		Assert.assertEquals("aaa", c.a());
		Assert.assertEquals("hi", c.b());
	}
}

interface I {
	public String a();
	default public String b() {
		return "hi";
	}
}

class C implements I {

	@Override
	public String a() {
		return "aaa";
	}
}