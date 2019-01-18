package jdk.java.lang;

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
	private static final Logger logger = LoggerFactory.getLogger(ClassClassTest.class);

	@Test
	public void shouldNotEquals() {
		Assert.assertNotEquals(String.class, String[].class);
	}

	@Test
	public void testGetClass() {
		MyClass my = new MyClass();
		Assert.assertEquals(MyClass.class, my.getClass());
		Assert.assertNotEquals(MyClass.class, my.getClass().getClass());
		Assert.assertEquals(Class.class, my.getClass().getClass());
		Assert.assertEquals(Class.class, my.getClass().getClass().getClass().getClass().getClass().getClass().getClass().getClass()); // 고만해
	}

	@Test
	public void testGetPackage() {
		Assert.assertEquals("jdk.java.lang", ClassClassTest.class.getPackage().getName());
	}

	@Test
	public void testForName() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> cls = Class.forName("jdk.java.lang.MyClass");
		Assert.assertEquals(java.lang.Class.class, cls.getClass());
		Assert.assertEquals("jdk.java.lang.MyClass", cls.getName());
		@SuppressWarnings("deprecation")
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
		Assert.assertEquals("jdk.java.lang.ClassClassTest$1", new Serializable() {}.getClass().getName());
	}

	@Test
	@SuppressWarnings("serial")
	public void testGetTypeName() {
		// standard
		Assert.assertEquals("java.util.AbstractMap", AbstractMap.class.getTypeName());
		// inner class
		Assert.assertEquals("java.util.AbstractMap$SimpleEntry", HashMap.SimpleEntry.class.getTypeName());
		// anonymous inner class, $2은 첫 번째로 생성된 익명 내부 클래스를 의미한다.
		Assert.assertEquals("jdk.java.lang.ClassClassTest$2", new Serializable() {}.getClass().getTypeName());
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

	@Test
	public void testCurrentMethodName() {
		// #1: 익명 클래스를 생성하고, 해당 클래스가 생성된 환경의 정보 중 메서드 이름을 가져오는 일종의 꼼수. 컴파일 시 불필요한 클래스가 생성되는것에 주의할 것
		final String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		Assert.assertEquals("testCurrentMethodName", methodName);

		/* 
		 * #2: 현재 쓰레드의 스택을 거꾸로 올라가서 메서드명을 가져오는 방법.
		 * 소스 출처: https://stackoverflow.com/questions/442747/getting-the-name-of-the-currently-executing-method
		 * 버추얼 머신에 따라 다른 결과가 나올 수 있다고 함. 
		 * 원문: Some virtual machines may, under some circumstances, omit one or more stack frames from the stack trace. 
		 * 	In the extreme case, a virtual machine that has no stack trace information concerning 
		 * 	this thread is permitted to return a zero-length array from this method. 
		 */
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		logger.debug(Arrays.toString(ste));
		Assert.assertEquals("getStackTrace", ste[0].getMethodName());
		Assert.assertEquals("testCurrentMethodName", ste[1].getMethodName());
	}
	
	@Test
	public void testCurrentClassName() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		logger.debug(Arrays.toString(ste));
		Assert.assertEquals("jdk.java.lang.ClassClassTest", ste[1].getClassName());
	}
}

class My<T> {

}

class MyClass {

}