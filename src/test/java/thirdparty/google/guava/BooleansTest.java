package thirdparty.google.guava;

import com.google.common.primitives.Booleans;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class BooleansTest {

    @Test
    public void test() {
        assertEquals(5, Booleans.asList(false, true, false, true, true).size());
        assertEquals("true, false", Booleans.join(", ", true, false));
    }
}
