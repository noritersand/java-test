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
        int[] original = {10, 3, 5, 1, 6, 8, 2};

        // ## #1 복사할 길이를 지정하여 복사
        int[] copy1 = Arrays.copyOf(original, 3);
        assertThat(copy1).isEqualTo(new int[]{10, 3, 5}).isNotSameAs(original);

        // Arrays.copyOf()로 복사한 배열은 재할당이 가능하다. (List.copyOf(), Set.copyOf(), Map.copyOf()들은 안됨)
        copy1[0] = 128;
        assertThat(copy1).isEqualTo(new int[]{128, 3, 5}).isNotSameAs(original);

        // 전체 길이 그대로 복사해도 서로 다른 인스턴스다.
        int[] arrayNotSame = Arrays.copyOf(original, original.length);
        assertThat(arrayNotSame).isEqualTo(original).isNotSameAs(original);

        // ## #2 시작 범위와 범위를 지정하여 복사
        int[] copy2 = Arrays.copyOfRange(original, 2, 5);
        assertThat(copy2).isEqualTo(new int[]{5, 1, 6}).isNotSameAs(original);
    }
}
