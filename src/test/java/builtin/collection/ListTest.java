package builtin.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * {@link List} 테스트
 * 
 * @author fixalot
 * @since 2023-03-25
 */
@Slf4j
class ListTest {

    /**
     * <p>of() 테스트. 비슷한 Collections.EMPTY_LIST가 있는데 미묘하게 다르다.
     * <p>소스를 보면 ImmutableCollections.EMPTY_LIST를 반환한다.
     */
    @Test
    void getEmptyList() {
        List<Object> of = List.of();
        assertThat(of).isEmpty();

        // ⚠️ of()는 변경 불가능한 리스트(unmodifiable list)를 반환한다.
        assertThatThrownBy(() -> {
            of.add("a");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testOf() {
        List<String> alphabet = List.of("a", "b", "c");
        assertThat(alphabet).hasSize(3);
    }

    @Test
    void howToAvoidNpeWithForStatement() {
        List<String> texts = null;
        for (String s : npeSafe(texts)) {
            log.debug("s: {}", s);
        }
    }

    private Iterable<? extends String> npeSafe(List<String> texts) {
        return texts == null ? List.of() : texts;
    }

    /**
     * <p>List.copyOf(), Set.copyOf(), Map.copyOf()는 내부에서 변경 불가능한 리스트(unmodifiable list)를 생성해 반환한다.</p>
     * <p>Java 10에서 만들어졌음</p>
     */
    @Test
    void testCopyOf() {
        List<String> original = new ArrayList<>();
        original.add("Java");
        original.add("Python");

        List<String> copy = List.copyOf(original);
        assertThat(copy).isEqualTo(original);

        assertThatThrownBy(() -> {
            copy.add("nothing"); // 변형 불가
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage(null);
    }
}
