package builtin.statement.expressions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 배열 테스트 슈트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class ArrayTest {

    /**
     * 자바 Array는 indexOf()가 없음. contains()도 없다. Arrays에도 없으니 찾지 말자.
     */
    @Test
    void testIndexOfLike() {
        String[] arr = {"a", "b", "c"};
//        arr.indexOf("a");
//        arr.contains("a");
        assertThat(indexOf(arr, "b")).isEqualTo(1);
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
        assertThat(arr.length).isEqualTo(2);
        assertThat(arr[0].length).isEqualTo(3);
        assertThat(arr[1].length).isEqualTo(2);
    }

    @Test
    void join() {
        String[] arr = {"a", "b", "c"};
        assertThat(String.join(",", arr)).isEqualTo("a,b,c");
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
        assertThat(str).isEqualTo(new String[]{"1x2x6", "3x4"});
    }
}
