package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ArrayUtilsTest {

    @Test
    void testSubarray() {
        int[] arr = {1, 2, 3, 4, 5};
        assertArrayEquals(new int[]{1, 2}, ArrayUtils.subarray(arr, 0, 2));
        assertArrayEquals(new int[]{2}, ArrayUtils.subarray(arr, 1, 2));
        assertArrayEquals(new int[]{1, 2, 3}, ArrayUtils.subarray(arr, 0, 3));
        assertArrayEquals(new int[]{3, 4}, ArrayUtils.subarray(arr, 2, 4));
    }
}
