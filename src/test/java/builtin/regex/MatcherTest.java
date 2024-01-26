package builtin.regex;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * java.util.regex.Matcher 테스트
 *
 * @author fixalot
 * @since 2019-02-26
 */
@Slf4j
class MatcherTest {

    /**
     * 주어진 문자열의 리터럴 대체 문자열(??? regex 패턴 문자열인가...)을 반환한다.
     *
     * @author fixalot
     */
    @Test
    void testQuoteReplacement() {
        assertEquals("\\\\", Matcher.quoteReplacement("\\"));
        assertEquals("	", Matcher.quoteReplacement("\t"));
        assertEquals("'", Matcher.quoteReplacement("'"));
    }

    @Test
    void usingWithStringReplace() {
        String str = "a/b/c/d";
        str = str.replaceAll("/", Matcher.quoteReplacement("\\"));
        assertEquals("a\\b\\c\\d", str);

        // 반대로도 됨
        String str2 = "a\\b\\c\\d";
        str2 = str2.replaceAll(Matcher.quoteReplacement("\\"), "/");
        assertEquals("a/b/c/d", str2);
    }
}
