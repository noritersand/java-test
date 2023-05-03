package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class ErrorTest {

    @Test
    void test() {
        assertThrows(Error.class, () -> {
            throw new Error();
        });
    }
}
