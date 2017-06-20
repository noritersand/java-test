package laboratory.jdk.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

	@Test
	public void test() throws Exception {
		Object instance = new MyClass();
		Class<?> clazz = instance.getClass();
		
		// invoke method
		Method method = clazz.getDeclaredMethod("myMethod");
		Assert.assertNotNull(method);
		Assert.assertEquals("finally you found me!", method.invoke(instance));
		
		// access field
		Field field = clazz.getDeclaredField("myField");
		Assert.assertNotNull(field);
		Assert.assertEquals(0, field.get(instance));
	}
	
	@SuppressWarnings("unused")
	private class MyClass {
		public int myField = 0;
		
		public String myMethod() {
			return "finally you found me!";
		}
	}
}