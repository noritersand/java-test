package thirdparty.google.guava;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * google guava joiner test case
 *
 * @author fixalot
 * @since 2017-09-11
 */
@Slf4j
class JoinerTest {

    @Test
    void listJoin() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Joiner joiner = Joiner.on(", ");
        assertEquals("1, 2, 3", joiner.join(numbers));
    }
}
