package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * RandomStringUtils test suite
 *
 * @author fixalot
 * @since 2022-08-31
 */
@Slf4j
public class RandomStringUtilsTest {

    @Test
    void basicUsage() {
        log.debug("RandomStringUtils.random: {}", RandomStringUtils.random(10));
        log.debug("RandomStringUtils.randomAlphabetic: {}", RandomStringUtils.randomAlphabetic(5));
        log.debug("RandomStringUtils.randomAlphabetic: {}", RandomStringUtils.randomAlphabetic(0, 10));
        log.debug("RandomStringUtils.randomAlphanumeric: {}", RandomStringUtils.randomAlphanumeric(6));
        log.debug("RandomStringUtils.randomAscii: {}", RandomStringUtils.randomAscii(3));
        log.debug("RandomStringUtils.randomGraph: {}", RandomStringUtils.randomGraph(4));
        log.debug("RandomStringUtils.randomPrint: {}", RandomStringUtils.randomPrint(5));
    }

    @Test
    void testRandomAlphabetic() {
        assertEquals(3, RandomStringUtils.randomAlphabetic(3).length());
        assertEquals(4, RandomStringUtils.randomAlphabetic(4).length());
        assertEquals(5, RandomStringUtils.randomAlphabetic(5).length());
    }
}
