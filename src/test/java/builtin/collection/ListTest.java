package builtin.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * {@link List} 테스트
 * 
 * @author fixalot
 * @since 2023-03-25
 */
@Slf4j
public class ListTest {

    /**
     * <p>of() 테스트. 비슷한 Collections.EMPTY_LIST가 있는데 미묘하게 다르다.
     * <p>소스를 보면 ImmutableCollections.EMPTY_LIST를 반환한다.
     */
    @Test
    void getEmptyList() {
        List<Object> of = List.of();
        assertThat(of.size()).isEqualTo(0);
    }

    /**
     *
     */
    @Test
    void testOf() {
        List<String> alphabet = List.of("a", "b", "c");
        assertThat(alphabet.size()).isEqualTo(3);
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
}
