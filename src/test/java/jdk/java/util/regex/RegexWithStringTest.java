package jdk.java.util.regex;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * String과 함께하는 정규식 뿅뿅 파티!
 *
 * @author noritersand
 * @since 2020-03-02
 */
@Slf4j
public class RegexWithStringTest {

    @Test
    void shoulBeEquals() {
        assertEquals("AA1062329819", removeTail("AA1062329819_1"));
        assertEquals("AA1062329819", removeTail("AA1062329819_199A"));

        assertEquals("1", extractTail("AA1062329819_1"));
        assertEquals("199A", extractTail("AA1062329819_199A"));

        assertEquals("1", extractTailUsingApacheCommons("AA1062329819_1"));
        assertEquals("12A3", extractTailUsingApacheCommons("AA1062329819_12A3"));
    }

    public static String removeTail(String str) {
        return str.replaceAll("_\\w+", "");
    }

    public static String extractTail(String str) {
        return str.replaceAll("\\w+_", "");
    }

    public static String extractTailUsingApacheCommons(String str) {
        return StringUtils.substringAfter(str, "_");
    }
}
