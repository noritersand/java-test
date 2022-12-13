package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ObjectTest {

    @Test
    public void getHashCode() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        log.debug("{}", obj1.hashCode());
        log.debug("{}", obj2.hashCode());
        assertNotEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    public void getString() {
        Object obj1 = new Object();
        Object[] obj2 = {null};
        log.debug(obj1.toString());
        log.debug(obj2.toString());

        assertTrue(obj1.toString().startsWith("java.lang")); // 일반 객체는 패키지부터 시작
        assertTrue(obj2.toString().startsWith("[Ljava.lang")); // 배열은 [L 다음에 패키지 시작
    }
}
