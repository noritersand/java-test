package builtin.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

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
    @SuppressWarnings("ConstantConditions")
    @Test
    void testRequireNonNull() {
        // #1 넘겨진 인자가 null이면 NPE 발생시킴
        assertThatThrownBy(() -> {
            Objects.requireNonNull(null);
        }).isInstanceOf(NullPointerException.class);

        // #2 넘겨진 인자가 null이면 NPE 발생시키는 것까진 같지만, 에러 메시지를 별도로 지정함
        assertThatThrownBy(() -> {
            Objects.requireNonNull(null, "This is that message");
        }).isInstanceOf(NullPointerException.class).hasMessage("This is that message");

        // #2-1 람다 방식
        assertThatThrownBy(() -> {
            Objects.requireNonNull(null, () -> "This is also that message");
        }).isInstanceOf(NullPointerException.class).hasMessage("This is also that message");

        // #3 넘겨진 인자가 null이면 NPE 발생 대신 지정한 defaultObj를 반환함
        assertThat(Objects.requireNonNullElse(null, "default")).isEqualTo("default");

        // #3-1 람다 방식
        String dummy = null;
        assertThat(Objects.requireNonNullElseGet(dummy, () -> "Hello?")).isEqualTo("Hello?");
    }
}
