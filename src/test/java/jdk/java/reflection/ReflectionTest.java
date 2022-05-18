package jdk.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class ReflectionTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

	@Test
	public void test() throws Exception {
		Object instance = new MyClass();
		Class<?> clazz = instance.getClass();

		// invoke method
		Method method = clazz.getDeclaredMethod("myMethod");
		assertNotNull(method);
		assertEquals("finally you found me!", method.invoke(instance));
		assertEquals("myMethod", method.getName());

		// access field
		Field field = clazz.getDeclaredField("myField");
		assertNotNull(field);
		assertEquals(0, field.get(instance));
		assertEquals("myField", field.getName());
	}

	@SuppressWarnings("unused")
	private class MyClass {
		public int myField = 0;

		public String myMethod() {
			return "finally you found me!";
		}
	}
}
