package test.jdk;

import org.junit.Assert;
import org.junit.Test;

public class ClassTest {
	@Test
	public void testClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		System.out.println(Class.class);
//		System.out.println(String.class);
//		System.out.println(Class.forName("java.lang.String").getClass());
		
		MyClass my = new MyClass();
		System.out.println(my.getClass()); // class My
		
		Class<?> cls = Class.forName("test.jdk.MyClass");
		System.out.println(cls.getClass()); // class java.lang.Class
		System.out.println(cls.getName()); // MyClass
		MyClass my2 = (MyClass) cls.newInstance();
		System.out.println(my2.getClass());
	}
	
	@Test
	public void testDifferent() {
		Assert.assertNotEquals(String.class, String[].class);
	}
}

class My<T> {
	
}

class MyClass {
	
}