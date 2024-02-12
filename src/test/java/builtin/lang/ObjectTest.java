package builtin.lang;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class ObjectTest {

    @Test
    void testHashCode() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        log.debug("{}", obj1.hashCode());
        log.debug("{}", obj2.hashCode());
        assertThat(obj2.hashCode()).isNotEqualTo(obj1.hashCode());
    }

    @Test
    void testString() {
        Object obj1 = new Object();
        Object[] obj2 = {null};
        log.debug(obj1.toString());
        log.debug(obj2.toString());

        assertThat(obj1.toString().startsWith("java.lang")).isTrue(); // 일반 객체는 패키지부터 시작
        assertThat(obj2.toString().startsWith("[Ljava.lang")).isTrue(); // 배열은 [L 다음에 패키지 시작
    }

    @Test
    void typeRecognize() {
        Object str1 = "qwe";
        Object int1 = 12345;
        Object bool1 = true;
        assertThat(getClassName(str1)).isEqualTo("String");
        assertThat(getClassName(int1)).isEqualTo("Integer");
        assertThat(getClassName(bool1)).isEqualTo("Boolean");
    }

    private String getClassName(Object arg1) {
        log.debug("arg1.getClass(): {}", arg1.getClass());
        log.debug("arg1.getClass().getSimpleName(): {}", arg1.getClass().getSimpleName());

        // instanceof는 스위치 조건으로 사용할 수 없음
//        switch (arg1 instanceof String) {
//            case true:
//                return "String";
//        }

        // 차라리 Class.getSimpleName()으로 스위칭하는 게 나을 수도 있겠다.
        if (arg1 instanceof String) {
            return "String";
        } else if (arg1 instanceof Integer) {
            return "Integer";
        } else if (arg1 instanceof Boolean) {
            return "Boolean";
        } else {
            return "Unknown";
        }
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        // clone() 메서드로 복제가 잘 되는지 테스트
        ParentObject parentObject1 = new ParentObject();
        parentObject1.setName("Waldo");
        ParentObject clone1 = parentObject1.getClone();
        assertThat(clone1.getName()).isEqualTo(parentObject1.getName());
        assertThat(clone1).isNotSameAs(parentObject1).isNotEqualTo(parentObject1); // 서로 다름(=별도의 인스턴스)

        ParentObject parentObject2 = new ParentObject();
        parentObject2.setName("Waldo");
        ChildObject childObject = new ChildObject();
        childObject.setName("Waldo junior");
        parentObject2.setChildObject(childObject);

        ParentObject clone2 = parentObject2.getClone();
        assertThat(clone2.getName()).isEqualTo(parentObject2.getName());
        assertThat(clone2).isNotSameAs(parentObject2).isNotEqualTo(parentObject2); // 일단 서로 다르긴 함
        assertThat(clone2.getChildObject()).isSameAs(parentObject2.getChildObject()); // 근데 자식 객체는 같음(=얕은 복사
    }

    @Getter
    @Setter
    public static class ParentObject implements Cloneable {
        private String name;
        private ChildObject childObject;

        public ParentObject getClone() throws CloneNotSupportedException {
            return (ParentObject) this.clone();
        }
    }

    @Getter
    @Setter
    public static class ChildObject {
        private String name;


    }
}
