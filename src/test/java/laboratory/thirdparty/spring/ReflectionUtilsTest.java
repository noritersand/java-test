package laboratory.thirdparty.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

public class ReflectionUtilsTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtilsTest.class);

	@Test
	public void test() throws Exception {
		Object instance = new MyClass();

		Method method2 = ReflectionUtils.findMethod(instance.getClass(), "myMethod", Object.class);
		Assert.assertNull(method2);

		// invoke method
		Method method = ReflectionUtils.findMethod(instance.getClass(), "myMethod", String.class);
		Assert.assertNotNull(method);
//		method.setAccessible(true); // 스프링 reflectionUtils는 public만 되는건가?
		Assert.assertEquals(ReflectionUtils.invokeMethod(method, instance, "some-argument"), method.invoke(instance, "some-argument"));
		;

		// get field value
		Field field = ReflectionUtils.findField(instance.getClass(), "intValue");
		Assert.assertNotNull(field);
		Assert.assertEquals(ReflectionUtils.getField(field, instance), field.get(instance));
	}

	@Test
	public void figureoutWhatKindOfMethod() {
		Object instance = new MyClass();
		Method method = ReflectionUtils.findMethod(instance.getClass(), "myMethod");
		Method method2 = ReflectionUtils.findMethod(instance.getClass(), "myMethod", String.class);
		Assert.assertEquals(0, method.getParameters().length);
		Assert.assertEquals(1, method2.getParameters().length);
	}

	@Test
	public void findAllMethod() throws Exception {
		Object instance = new MyClass();
		Method[] a = ReflectionUtils.getAllDeclaredMethods(instance.getClass());
		Method[] b = ReflectionUtils.getUniqueDeclaredMethods(instance.getClass());
		// superclass와 subclass에 선언된 메서드 모두 출력
		logger.debug(Arrays.toString(a));
		// 'leaf class method'만 출력한다. (subclass에서 오버라이딩한 메서드가 있을땐 superclass의 메서드를 생략)
		logger.debug(Arrays.toString(b));
	}

	@Test
	public void findExactlyIWant() throws Exception {
		Object instance = new MyClass();
		Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(instance.getClass());
		for (Method method : methods) {
			if ("myMethod".equals(method.getName())) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length != 0) {
					if (parameterTypes[0] == String.class) {
						Assert.assertEquals("hey", method.invoke(instance, "hey"));
						;
					}
				} else {
					Assert.assertEquals("hello. my name is skywalker", method.invoke(instance));
				}
			}
		}
	}

	public class MyClass extends Father {
		public int intValue = 0;
		public Double doubleValue = 0.1;

		public String myMethod() {
			return "hello. my name is skywalker";
		}

		public String myMethod(String param) {
			return param;
		}

		@Override
		public String sayMyName() {
			return super.sayMyName();
		}
	}

	public class Father {
		public String sayMyName() {
			return "i'm your father";
		}
	}
}
