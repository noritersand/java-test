package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void test() {
        // 기본 사용 방법
        int i;
        for (i = 0; 1 > i; i++) {
            log.debug("{} 바퀴", i);
        }
        assertEquals(1, i);

        // 무한 루프
        for (; ; ) {
            log.debug("비상탈출!");
            break;
        }

        // 이런 모양으로도 됨.
        int j = 0;
        while (1 > j) {
            j++;
            log.debug("{} 바퀴", j);
        }
    }

    @Test
    public void testIterator() {
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

    @Test
    public void testAdvencedForStatement() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        for (String s : list) {
            log.debug("s: {}", s);
        }

        // null이면 어떻게 될까?
        list = null;
//        for (String s : list) {
//            log.debug("s: {}", s);
//        }
        // NPE 발생함
    }
}
