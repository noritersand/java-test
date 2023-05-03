package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class StringUtilsTest {

    @Test
    void testDefault() {
        assertEquals("", StringUtils.defaultString("", "0"));
        assertEquals("0", StringUtils.defaultIfBlank("", "0"));
    }

    @Test
    void testPadding() {
        assertEquals("001", StringUtils.leftPad("1", 3, "0"));
        assertEquals("10000", StringUtils.rightPad("1", 5, "0"));
    }
}
