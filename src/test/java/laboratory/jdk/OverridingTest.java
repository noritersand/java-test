package laboratory.jdk;

import org.junit.Test;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class OverridingTest {
	@Test
	public void override() {

	}
}

class Parent {
	public void m01() {

	}

	public static void m02() {

	}
}

class Child extends Parent {
	@Override
	public void m01() {

	}

	public static void m02() {

	}
}