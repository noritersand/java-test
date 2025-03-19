package builtin.regex;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * java.util.regex.Matcher 테스트
 *
 * @author fixalot
 * @since 2019-02-26
 */
@Slf4j
class RegexMatcherTest {

    /**
     * 주어진 문자열의 리터럴 대체 문자열(regex 패턴 문자열인가?)을 반환한다.
     *
     * @author fixalot
     */
    @Test
    void testQuoteReplacement() {
        assertThat(Matcher.quoteReplacement("\\")).isEqualTo("\\\\");
        assertThat(Matcher.quoteReplacement("\t")).isEqualTo("	");
        assertThat(Matcher.quoteReplacement("'")).isEqualTo("'");
    }

    @Test
    void usingWithStringReplace() {
        String str = "a/b/c/d";
        str = str.replaceAll("/", Matcher.quoteReplacement("\\"));
        assertThat(str).isEqualTo("a\\b\\c\\d");

        // 반대로도 됨
        String str2 = "a\\b\\c\\d";
        str2 = str2.replaceAll(Matcher.quoteReplacement("\\"), "/");
        assertThat(str2).isEqualTo("a/b/c/d");
    }

    /**
     * Matcher.matches() 는 문자열 전체가 정규식 패턴과 일치해야 true를 반환한다.
     */
    @Test
    void testMatches() {
        String url = "/wallet/app/auth/v1/app-version";
        String pattern = "/wallet/app/.*";
        boolean result = Pattern.compile(pattern).matcher(url).matches();
        assertThat(result).isTrue(); // 전체 일치라서 true

        String url2 = "/wallet/app/auth/v1/app-version";
        String pattern2 = "/app/.*";
        boolean result2 = Pattern.compile(pattern2).matcher(url2).matches();
        assertThat(result2).isFalse(); // 부분 일치는 false
    }

    /**
     * Matcher.find() 는 문자열 일부분만 정규식 패턴과 일치해도 true를 반환한다.
     */
    @Test
    void testFind() {
        String url = "/wallet/app/auth/v1/app-version";
        String pattern = "/wallet/app/.*";
        boolean result = Pattern.compile(pattern).matcher(url).find();
        assertThat(result).isTrue(); // 전체 일치도 true

        String url2 = "/wallet/app/auth/v1/app-version";
        String pattern2 = "/app/.*";
        boolean result2 = Pattern.compile(pattern2).matcher(url2).find();
        assertThat(result2).isTrue(); // 부분 일치도 true
    }

}
