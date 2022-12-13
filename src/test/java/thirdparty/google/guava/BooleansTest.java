package thirdparty.google.guava;

import com.google.common.primitives.Booleans;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class BooleansTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(BooleansTest.class);

    @Test
    public void test() {
        assertEquals(5, Booleans.asList(false, true, false, true, true).size());
        assertEquals("true, false", Booleans.join(", ", true, false));
    }
}
