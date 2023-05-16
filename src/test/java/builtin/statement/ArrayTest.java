package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 배열 테스트 슈트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ArrayTest {

    /**
     * 자바 Array는 indexOf()가 없음. contains()도 없다. Arrays에도 없으니 찾지 말자.
     */
    @Test
    void testIndexOfLike() {
        String[] arr = {"a", "b", "c"};
//        arr.indexOf("a");
//        arr.contains("a");
        assertEquals(1, indexOf(arr, "b"));;
    }

    private int indexOf(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            // comparing element to the target element
            if (target.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Test
    void getLength() {
        int[][] arr = {{1, 2, 6}, {3, 4}};
        assertEquals(2, arr.length);
        assertEquals(3, arr[0].length);
        assertEquals(2, arr[1].length);
    }

    @Test
    void join() {
        String[] arr = {"a", "b", "c"};
        assertEquals("a,b,c", String.join(",", arr));
    }

    @Test
    void testFindFirstMatchUsingForStatement() {
        String[] arr = {"/findme", "/beginning", "/start"};
        final String URL = "/beginning/list/hello";
        for (String e : arr) {
            if (0 == URL.indexOf(e)) {
                log.debug("URL: {}", URL);
                log.debug("e: {}", e);
            }
        }
    }

    @Test
    void getString() {
        int[][] arr = {{1, 2, 6}, {3, 4}};
        String[] str = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (null == str[i]) {
                    str[i] = "";
                }
                if (0 != j) {
                    str[i] += "x";
                }
                str[i] += String.valueOf(arr[i][j]);
            }
        }
        assertArrayEquals(new String[]{"1x2x6", "3x4"}, str);
    }
}
