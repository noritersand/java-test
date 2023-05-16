package builtin.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * java.util.Objects 테스트 슈ㅌ
 */
@Slf4j
public class ObjectsTest {

    @Test
    void test() {

    }

    /**
     * requireNonNull()과 파생 메서드 테스트
     */
    @Test
    void testRequireNonNull() {
        // #1 넘겨진 인자가 null이면 NPE 발생시킴
        assertThrows(NullPointerException.class, () -> {
            Objects.requireNonNull(null);
        });


        // #2 넘겨진 인자가 null이면 NPE 발생시키는 것까진 같지만, 에러 메시지를 별도로 지정함
        assertThrows(NullPointerException.class, () -> {
            Object thisIsThatMessage = Objects.requireNonNull(null, "This is that message");
        });

        // #3 넘겨진 인자가 null이면 NPE 발생 대신 지정한 defaultObj를 반환함
        assertEquals("default", Objects.requireNonNullElse(null, "default"));
    }
}
