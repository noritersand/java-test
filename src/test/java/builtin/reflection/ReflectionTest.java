package builtin.reflection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.*;

/**
 * 리플렉션 테스트 슈트
 * 
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ReflectionTest {
    /**
     * 같은 타입의 인스턴스 두 개를 비교해서 값이 다른 필드만 고르기
     */
    @Test
    void compareInstances() throws IllegalAccessException {
        MyClass3 entity1 = new MyClass3(123, "abc", true, false, true, true, false);
        MyClass3 entity2 = new MyClass3(456, "qwe", true, true, false, true, true);

        // 변경된 필드 목록
        List<String> changedFields = new ArrayList<>();
        for (Field field : MyClass3.class.getDeclaredFields()) {
            field.setAccessible(true);
            // Boolean 필드만
            if (field.getType() == Boolean.class) {
                Boolean v1 = (Boolean) field.get(entity1);
                Boolean v2 = (Boolean) field.get(entity2);
                if (!v1.equals(v2)) {
                    changedFields.add(field.getName());
                }
            }
        }

        // 서로 다른 필드 중 Boolean 타입만 골랐기 때문에 flag2, flag3, flag5만 있음
        assertThat(changedFields).hasSize(3)
                .doesNotContain("number", "text", "flag1", "flag4")
                .containsExactly("flag2", "flag3", "flag5");
    }


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

@Getter
@Setter
@NoArgsConstructor
class MyClass3 {
    private Integer number;
    private String text;
    private Boolean flag1;
    private Boolean flag2;
    private Boolean flag3;
    private Boolean flag4;
    private Boolean flag5;

    public MyClass3(Integer number, String text, Boolean flag1, Boolean flag2, Boolean flag3, Boolean flag4, Boolean flag5) {
        this.number = number;
        this.text = text;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.flag5 = flag5;
    }
}
