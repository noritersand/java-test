package thirdparty.google.guava;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * google guava joiner test case
 *
 * @author fixalot
 * @since 2017-09-11
 */
public class JoinerTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(JoinerTest.class);

    @Test
    public void listJoin() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Joiner joiner = Joiner.on(", ");
        assertEquals("1, 2, 3", joiner.join(numbers));
    }
}
