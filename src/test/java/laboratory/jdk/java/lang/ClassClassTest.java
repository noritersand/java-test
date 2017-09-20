package laboratory.jdk.java.lang;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;

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
public class ClassClassTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ClassClassTest.class);

	@Test
	public void shouldNotEquals() {
		Assert.assertNotEquals(String.class, String[].class);
	}

	@Test
	public void testGetClass() {
		MyClass my = new MyClass();
		Assert.assertEquals(MyClass.class, my.getClass());
		Assert.assertEquals(Class.class, my.getClass().getClass());
		Assert.assertEquals(Class.class, my.getClass().getClass().getClass().getClass().getClass());
	}

	@Test
	public void testGetPackage() {
		Assert.assertEquals("laboratory.jdk.java.lang", ClassClassTest.class.getPackage().getName());
	}

	@Test
	public void testForName() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> cls = Class.forName("laboratory.jdk.java.lang.MyClass");
		Assert.assertEquals(java.lang.Class.class, cls.getClass());
		Assert.assertEquals("laboratory.jdk.java.lang.MyClass", cls.getName());
		MyClass my2 = (MyClass) cls.newInstance();
		Assert.assertEquals(MyClass.class, my2.getClass());
	}

	@Test
	@SuppressWarnings("serial")
	public void testGetName() {
		// standard
		Assert.assertEquals("java.util.AbstractMap", AbstractMap.class.getName());
		// inner class
		Assert.assertEquals("java.util.AbstractMap$SimpleEntry", HashMap.SimpleEntry.class.getName());
		// anonymous inner class, $1은 첫 번째로 생성된 익명 내부 클래스를 의미한다.
		Assert.assertEquals("laboratory.jdk.java.lang.ClassClassTest$1", new Serializable() {}.getClass().getName());
	}

	@Test
	@SuppressWarnings("serial")
	public void testGetTypeName() {
		// standard
		Assert.assertEquals("java.util.AbstractMap", AbstractMap.class.getTypeName());
		// inner class
		Assert.assertEquals("java.util.AbstractMap$SimpleEntry", HashMap.SimpleEntry.class.getTypeName());
		// anonymous inner class, $2은 첫 번째로 생성된 익명 내부 클래스를 의미한다.
		Assert.assertEquals("laboratory.jdk.java.lang.ClassClassTest$2", new Serializable() {}.getClass().getTypeName());
	}

	@Test
	@SuppressWarnings("serial")
	public void testGetCanonicalName() {
		// standard
		Assert.assertEquals("java.util.AbstractMap", AbstractMap.class.getCanonicalName());
		// inner class
		Assert.assertEquals("java.util.AbstractMap.SimpleEntry", HashMap.SimpleEntry.class.getCanonicalName());
		// anonymous inner class
		Assert.assertEquals(null, new Serializable() {}.getClass().getCanonicalName());
	}

	@Test
	@SuppressWarnings("serial")
	public void testGetSimpleName() {
		// standard
		Assert.assertEquals("AbstractMap", AbstractMap.class.getSimpleName());
		// inner class
		Assert.assertEquals("SimpleEntry", HashMap.SimpleEntry.class.getSimpleName());
		// anonymous inner class
		Assert.assertEquals("", new Serializable() {}.getClass().getSimpleName());
	}

	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * 
	 * 소스 출처: <a href="https://stackoverflow.com/questions/442747/getting-the-name-of-the-currently-executing-method">링크</a>
	 * 
	 * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */
	public static String getMethodName(final int depth) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		logger.debug(Arrays.toString(ste));

		// System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
		// return ste[ste.length - depth].getMethodName(); //Wrong, fails for depth = 0
		return ste[ste.length - 1 - depth].getMethodName(); // Thank you Tom Tresansky
	}

	@Test
	public void testCurrentMethodName() {
		// #1: 익명 클래스를 생성하고, 해당 클래스가 생성된 환경의 정보 중 메서드 이름을 가져오는 일종의 꼼수. 컴파일 시 불필요한 클래스가 생성되는것에 주의할 것
		final String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		Assert.assertEquals("testCurrentMethodName", methodName);

		// #2: 현재 쓰레드의 스택을 거꾸로 거꾸로 올라가서 메서드명을 가져오는 방법.
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		logger.debug(Arrays.toString(ste));
		Assert.assertEquals("getStackTrace", ste[0].getMethodName());
		Assert.assertEquals("testCurrentMethodName", ste[1].getMethodName());
	}
}

class My<T> {

}

class MyClass {

}