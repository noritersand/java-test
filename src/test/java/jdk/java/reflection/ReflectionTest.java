package jdk.java.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ReflectionTest {

    @Test
    public void test() throws Exception {
        Object instance = new MyClass();
        Class<?> clazz = instance.getClass();

        // 메서드 찾기
        Method method1 = clazz.getDeclaredMethod("withoutParameter");
        assertNotNull(method1);
        // invoke method
        assertEquals("withoutParameter", method1.getName());
        assertEquals("finally you found me!", method1.invoke(instance));

        // 메서드 찾기: 파라미터가 있는 메서드는 타입을 지정해줘야 찾음
        Method method2 = clazz.getDeclaredMethod("withParameter", String.class);
        assertNotNull(method2);
        assertEquals("withParameter", method2.getName());
        assertEquals("oOoOoOoOo", method2.invoke(instance, "oOoOoOoOo"));

        // access field
        Field field = clazz.getDeclaredField("myField");
        assertNotNull(field);
        field.setAccessible(true);
        assertEquals("myField", field.getName());
        assertEquals(0, field.get(instance));
    }

}

class MyClass {
    private final int myField = 0;

    public String withoutParameter() {
        return "finally you found me!";
    }

    public String withParameter(String txt) {
        return txt;
    }
}
