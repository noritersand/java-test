package builtin.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ReflectionTest {

    @Test
    void test() throws Exception {
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

    @Test
    void test2() throws IllegalAccessException {
        List<String> randomtxts = List.of("a", "b", "c");

        MyClass2 data = new MyClass2();
        data.setList(randomtxts);

        Class<? extends Object> clazz = data.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(List.class)) {
                field.setAccessible(true);
                List<String> list = (List<String>) field.get(data);
                assertEquals(List.of("a", "b", "c"), list);
                for (String s : list) {
                    log.debug("s: {}", s);
                }
            }
        }
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

class MyClass2 {
    private int num = 0;
    private List<String> list;

    public int getNum() {
        return this.num;
    }

    public List<String> getList() {
        return this.list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
