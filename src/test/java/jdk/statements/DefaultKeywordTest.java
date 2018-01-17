package jdk.statements;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
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