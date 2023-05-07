package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ObjectTest {

    @Test
    void getHashCode() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        log.debug("{}", obj1.hashCode());
        log.debug("{}", obj2.hashCode());
        assertNotEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    void getString() {
        Object obj1 = new Object();
        Object[] obj2 = {null};
        log.debug(obj1.toString());
        log.debug(obj2.toString());

        assertTrue(obj1.toString().startsWith("java.lang")); // 일반 객체는 패키지부터 시작
        assertTrue(obj2.toString().startsWith("[Ljava.lang")); // 배열은 [L 다음에 패키지 시작
    }

    @Test
    void typeRecognize() {
        Object str1 = "qwe";
        Object int1 = 12345;
        Object bool1 = true;
        assertEquals("String", getClassName(str1));
        assertEquals("Integer", getClassName(int1));
        assertEquals("Boolean", getClassName(bool1));
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
}
