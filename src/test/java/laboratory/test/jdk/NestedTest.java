package laboratory.test.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NestedTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(NestedTest.class);

	@Test
	public void test() {
		NonStaticOuter outer = new NonStaticOuter();
		Assert.assertEquals("invoking outerMethod", outer.executeOuterMethod());

		NonStaticOuter.Inner inner = new NonStaticOuter().getInner();
		Assert.assertEquals("invoking innerMethod", inner.executeInnerMethod());
	}

	@Test
	public void test2() {
		new Outer().outerMethod();
	}
}

class NonStaticOuter {
	private String text = "outer";

	public Inner getInner() {
		return new Inner();
	}

	public class Inner {
		private String text = "inner";

		public String executeInnerMethod() {
			return "invoking " + text + "Method";
		}
	}

	public String executeOuterMethod() {
		return "invoking " + text + "Method";
	}
}

class Outer {
	private static String a = "static outer member";
	private String b = "non-static outer member";

	private class Inner {
//		private static String c = "static inner member"; // non-static 내부 클래스에는 스태틱 멤버를 선언할 수 없음
		private String d = "non-static inner member";

		private void innerMethod() {
			System.out.println(Outer.a);

			Outer out = new Outer();
			System.out.println(out.b);
			System.out.println(this.d);
		}
	}

	public void outerMethod() {
		System.out.println(a);
		System.out.println(this.b);

		Inner inner = new Inner();
		System.out.println(inner.d);
		inner.innerMethod();
	}
}
