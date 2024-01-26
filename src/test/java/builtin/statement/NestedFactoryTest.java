package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * non-static 중첩 클래스 공장장 테스트
 *
 * @author fixalot
 * @since 2019-02-25
 */
@Slf4j
class NestedFactoryTest {

    @Test
    void test() {
        // #1 내부 클래스가 public일 때: 인스턴스 직접 접근
        NestedClassFactory.NestedClass inner = NestedClassFactory.getNestedClass();
        assertNotNull(inner);

        // #2 내부 클래스가 private일 때: 인스턴스 직접 접근 불가.
        // compile error: The type NestedClassFactory.NestedHiddenClass is not visible
        // NestedClassFactory.NestedHiddenClass inner2 = NestedClassFactory.getNestedHiddenClass();
        assertEquals("fire egg", NestedClassFactory.getValueOfNestedHiddenClass());
    }
}

