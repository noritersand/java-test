package jdk.statement;

import jdk.statement.NestedClassFactory.NestedClass;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * non-static 중첩 클래스 공장장 테스트
 *
 * @author fixalot
 * @since 2019-02-25
 */
public class NestedFactoryTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(NestedFactoryTest.class);

    @Test
    public void test() {
        // #1 내부 클래스가 public일 때: 인스턴스 직접 접근
        NestedClass inner = NestedClassFactory.getNestedClass();
        assertNotNull(inner);

        // #2 내부 클래스가 private일 때: 인스턴스 직접 접근 불가.
        // compile error: The type NestedClassFactory.NestedHiddenClass is not visible
        // NestedClassFactory.NestedHiddenClass inner2 = NestedClassFactory.getNestedHiddenClass();
        assertEquals("fire egg", NestedClassFactory.getValueOfNestedHiddenClass());
    }
}

