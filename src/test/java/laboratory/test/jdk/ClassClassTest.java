package laboratory.test.jdk;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassClassTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ClassClassTest.class);
	
	@Test
	public void testClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		System.out.println(Class.class);
//		System.out.println(String.class);
//		System.out.println(Class.forName("java.lang.String").getClass());
		
		MyClass my = new MyClass();
		System.out.println(my.getClass()); // class My
		
		Class<?> cls = Class.forName("laboratory.test.jdk.MyClass");
		System.out.println(cls.getClass()); // class java.lang.Class
		System.out.println(cls.getName()); // MyClass
		MyClass my2 = (MyClass) cls.newInstance();
		System.out.println(my2.getClass());
	}
	
	@Test
	public void testDifferent() {
		Assert.assertNotEquals(String.class, String[].class);
	}

	@Test
	public void testGetClass() {
		Assert.assertEquals(new String().getClass(), String.class);
	}
	
	@Test
	public void testNewInstanceByName() throws Exception {
		Class<?> clazz = Class.forName("java.util.Date");
		Date date = (Date) clazz.newInstance();
		Assert.assertEquals("java.util.Date", date.getClass().getName());
	}
}

class My<T> {
	
}

class MyClass {
	
}