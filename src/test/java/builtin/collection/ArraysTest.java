package builtin.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ArraysTest {

    @Test
    void testToString() {
        String[] a = {"a", "b", "c"};
        log.debug(Arrays.toString(a));
    }

    @Test
    void shouldEqual() {
        String[] a = {"a", "b", "c"};
        String[] b = {"a", "b", "c"};
        assertArrayEquals(a, b);
    }

    @Test
    void testAsList() {
        List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
        List<Integer> list2 = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
        assertEquals(list, list2);
    }

    @Test
    void testSort() {
        int[] ns = {10, 3, 5, 1, 6, 8, 2};
        Arrays.sort(ns);
        assertArrayEquals(ns, new int[]{1, 2, 3, 5, 6, 8, 10});

        ns = new int[]{10, 3, 5, 1, 6, 8, 2};
        Arrays.sort(ns, 2, ns.length); // 세 번째 요소부터 마지막 요소만 내림차순 정렬
        assertArrayEquals(ns, new int[]{10, 3, 1, 2, 5, 6, 8});
    }
}
