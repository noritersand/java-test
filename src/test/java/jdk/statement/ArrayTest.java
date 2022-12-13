package jdk.statement;

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

    @Test
    public void getString() {
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

    @Test
    public void getLength() {
        int[][] arr = {{1, 2, 6}, {3, 4}};
        assertEquals(2, arr.length);
        assertEquals(3, arr[0].length);
        assertEquals(2, arr[1].length);
    }

    @Test
    public void join() {
        String[] arr = {"a", "b", "c"};
        assertEquals("a,b,c", String.join(",", arr));
    }

    @Test
    public void testFindFirstMatchUsingForStatement() {
        String[] arr = {"/findme", "/beginning", "/start"};
        final String URL = "/beginning/list/hello";
        for (String e : arr) {
            if (0 == URL.indexOf(e)) {
                log.debug("URL: {}", URL);
                log.debug("e: {}", e);
            }
        }
    }
}
