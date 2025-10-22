package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class StringUtilsTest {

    @Test
    void testIsBlank() {
        assertThat(StringUtils.isBlank(null)).isTrue();
        assertThat(StringUtils.isBlank("")).isTrue();
        assertThat(StringUtils.isBlank(" ")).isTrue();
    }

    @Test
    void testDefault() {
        // null일 때만 defaultString으로 대체
        assertThat(StringUtils.defaultString(null, "0")).isEqualTo("0");
        assertThat(StringUtils.defaultString("", "0")).isEqualTo("");
        assertThat(StringUtils.defaultString(" ", "0")).isEqualTo(" ");
        assertThat(StringUtils.defaultString("bat", "0")).isEqualTo("bat");

        // null과 null-string일 때 defaultString으로 대체
        assertThat(StringUtils.defaultIfEmpty(null, "0")).isEqualTo("0");
        assertThat(StringUtils.defaultIfEmpty("", "0")).isEqualTo("0");
        assertThat(StringUtils.defaultIfEmpty(" ", "0")).isEqualTo(" ");
        assertThat(StringUtils.defaultIfEmpty("bat", "0")).isEqualTo("bat");

        // null과 null-string, blank-string일 때 defaultString으로 대체
        assertThat(StringUtils.defaultIfBlank(null, "0")).isEqualTo("0");
        assertThat(StringUtils.defaultIfBlank("", "0")).isEqualTo("0");
        assertThat(StringUtils.defaultIfBlank(" ", "0")).isEqualTo("0");
        assertThat(StringUtils.defaultIfBlank("bat", "0")).isEqualTo("bat");
    }

    @Test
    void testPadding() {
        assertThat(StringUtils.leftPad("1", 3, "0")).isEqualTo("001");
        assertThat(StringUtils.rightPad("1", 5, "0")).isEqualTo("10000");
    }
}
