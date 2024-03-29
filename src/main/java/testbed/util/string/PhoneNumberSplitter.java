package testbed.util.string;

import testbed.exception.serverfault.InappropriateArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 전화번호 쪼개기<br>
 * <p>
 * 쪼깨는 정규식 <a href="https://m.blog.naver.com/PostView.nhn?blogId=ppogry3&logNo=220549495377&proxyReferer=https%3A%2F%2Fwww.google.com%2F">소스 출처</a>
 *
 * @author fixalot
 * @since 2019-05-28
 */
@Slf4j
public enum PhoneNumberSplitter {
    ;
    public static final String DELIMITER = "-";
    public static final String REGEX = "^(01\\d{1}|02|0505|0502|0506|0\\d{1,2})-?(\\d{3,4})-?(\\d{4})";

    public static Result split(String str) {
        if (StringUtils.isEmpty(str) || 9 > str.length()) {
            throw new InappropriateArgumentException("전화번호는 필수값이며 9자리 이상이어야 해요.");
        }

        Result result = new Result();
        result.setOriginal(str);

        Pattern tellPattern = Pattern.compile(REGEX);
        Matcher matcher = tellPattern.matcher(str);
        String[] separated;
        if (matcher.matches()) {
            separated = new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
        } else {
            if (11 > str.length()) {
                throw new InappropriateArgumentException("뭔가 이상한 전화번호에요");
            }
            String str1 = str.substring(0, 3);
            String str2 = str.substring(3, 7);
            String str3 = str.substring(7, 11);
            separated = new String[]{str1, str2, str3};
        }
        result.setSeparated(separated);

        List<String> strList = Arrays.asList(separated);
        String joined = String.join(DELIMITER, strList);
        result.setJoined(joined);
        return result;
    }

    public static class Result {
        private String original;
        private String[] separated;
        private String joined;

        public String getOriginal() {
            return original;
        }

        private void setOriginal(String original) {
            this.original = original;
        }

        public String[] getSeparated() {
            return separated;
        }

        private void setSeparated(String[] separated) {
            this.separated = separated;
        }

        public String getJoined() {
            return joined;
        }

        private void setJoined(String joined) {
            this.joined = joined;
        }
    }
}
