package builtin.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class ArraysTest {

    @Test
    void testToString() {
        String[] a = {"a", "b", "c"};
        log.debug(Arrays.toString(a));
    }

    @Test
    void shouldEqual() {
        String[] a = {"a", "b", "c"};
        String[] b = {"a", "b", "c"};
        assertThat(b).isEqualTo(a);
    }

    @Test
    void testAsList() {
        List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
        List<Integer> list2 = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
        assertThat(list2).isEqualTo(list);

        // ⚠️ Arrays.asList()로 만든 리스트는 고정 크기이다. 추가/삭제 불가능
        assertThatThrownBy(() -> {
            list.add(123);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testSort() {
        int[] ns = {10, 3, 5, 1, 6, 8, 2};
        Arrays.sort(ns);
        assertThat(ns).isEqualTo(new int[]{1, 2, 3, 5, 6, 8, 10});

        ns = new int[]{10, 3, 5, 1, 6, 8, 2};
        Arrays.sort(ns, 2, ns.length); // 세 번째 요소부터 마지막 요소만 내림차순 정렬
        assertThat(ns).isEqualTo(new int[]{10, 3, 1, 2, 5, 6, 8});
    }

    /**
     * <p>Arrays.copyOf()는 배열을 복사할 때 사용한다.</p>
     * <p>길이를 항상 명시해야 게 꼭 subarray() 같다.</p>
     */
    @Test
    void testCopyOf() {
        int[] ns = {10, 3, 5, 1, 6, 8, 2};
        int[] ns2 = Arrays.copyOf(ns, 3);
        assertThat(ns2).isEqualTo(new int[]{10, 3, 5});

        int[] ns3 = Arrays.copyOfRange(ns, 2, 5);
        assertThat(ns3).isEqualTo(new int[]{5, 1, 6});
    }
}
