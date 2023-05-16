package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * for문 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ForStatementTest {

    /**
     * usage
     *
     * @author fixalot
     */
    @Test
    void test() {
        // 기본 사용 방법
        int i;
        for (i = 0; 1 > i; i++) {
            log.debug("i: {} 바퀴", i);
        }
        assertEquals(1, i);

        // 무한 루프
        int k = 0;
        for (; ; ) {
            ++k;
            if (k > 2) {
                log.debug("비상탈출!");
                break;
            }
        }
        assertEquals(3, k);

        // while로 하면 이렇게
        int j = 0;
        while (2 > j) {
            j++;
            log.debug("j: {} 바퀴", j);
        }
        assertEquals(2, j);
    }

    @Test
    void testIterator() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            log.debug(iterator.next());
            // 마지막 요소 삭제
            if (!iterator.hasNext()) {
                iterator.remove();
            }
        }
        assertEquals(3, list.size());
    }

    /**
     * <p>enhanced for문이라 불리는 for-each 테스트</p>
     */
    @Test
    void testEnhancedForStatement() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        for (String s : list) {
            log.debug("s: {}", s);
        }

        // null이면 어떻게 될까?
        assertThrows(NullPointerException.class, () -> {
            ArrayList<String> list2 = null;
            for (String s : list2) {
                log.debug("s: {}", s);
            }
        });

    }
}
