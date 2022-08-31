package thirdparty.apache.commons;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RandomStringUtils test suite
 *
 * @author fixalot
 * @since 2022-08-31
 */
public class RandomStringUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(RandomStringUtilsTest.class);

    @Test
    public void basicUsage() {
        logger.debug("RandomStringUtils.random: {}", RandomStringUtils.random(10));
        logger.debug("RandomStringUtils.randomAlphabetic: {}", RandomStringUtils.randomAlphabetic(5));
        logger.debug("RandomStringUtils.randomAlphabetic: {}", RandomStringUtils.randomAlphabetic(0, 10));
        logger.debug("RandomStringUtils.randomAlphanumeric: {}", RandomStringUtils.randomAlphanumeric(6));
        logger.debug("RandomStringUtils.randomAscii: {}", RandomStringUtils.randomAscii(3));
        logger.debug("RandomStringUtils.randomGraph: {}", RandomStringUtils.randomGraph(4));
        logger.debug("RandomStringUtils.randomPrint: {}", RandomStringUtils.randomPrint(5));
    }

    @Test
    public void testRandomAlphabetic() {
        assertEquals(3, RandomStringUtils.randomAlphabetic(3).length());
        assertEquals(4, RandomStringUtils.randomAlphabetic(4).length());
        assertEquals(5, RandomStringUtils.randomAlphabetic(5).length());
    }
}
