package builtin.regex;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * String과 함께하는 정규식 뿅뿅 파티!
 *
 * @author noritersand
 * @since 2020-03-02
 */
@Slf4j
class RegexInStringClassTest {

    @Test
    void shoulBeEquals() {
        assertThat(removeTail("AA1062329819_1")).isEqualTo("AA1062329819");
        assertThat(removeTail("AA1062329819_199A")).isEqualTo("AA1062329819");

        assertThat(extractTail("AA1062329819_1")).isEqualTo("1");
        assertThat(extractTail("AA1062329819_199A")).isEqualTo("199A");

        assertThat(extractTailUsingApacheCommons("AA1062329819_1")).isEqualTo("1");
        assertThat(extractTailUsingApacheCommons("AA1062329819_12A3")).isEqualTo("12A3");
    }

    static String removeTail(String str) {
        return str.replaceAll("_\\w+", "");
    }

    static String extractTail(String str) {
        return str.replaceAll("\\w+_", "");
    }

    static String extractTailUsingApacheCommons(String str) {
        return StringUtils.substringAfter(str, "_");
    }

    @Test
    void test1() {
        String pn = "010-1234-1234";
        String regex = "(\\d{3}-\\d{4}-)(\\d{4})";
        String maskedNumber = pn.replaceAll(regex, "***-****-$2");
        assertThat(maskedNumber).isEqualTo("***-****-1234");
    }

}
