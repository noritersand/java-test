package builtin.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class StringTest {

    @Test
    void testFormat() {
        // %05d는 5자리 숫자를 의미하며, 숫자가 5자리보다 작으면 앞에 0을 채운다.
        String result = String.format("%05d", 1);
        assertThat(result).isEqualTo("00001");
    }

    @Test
    void testCustomFormatter() {
        class Formatter {
            String floatFormat(double number) {
                return String.format("%.2f", number); // dot(.)과 바로 뒤에오는 숫자는 정밀도를 의미한다.
            }

            String binaryFormat(double number) {
                return String.format("%.0f", number);
            }
        }
        Formatter formatter = new Formatter();

        // 소수점 2자리까지  표현
        assertThat(formatter.floatFormat(1.0D)).isEqualTo("1.00");
        assertThat(formatter.floatFormat(100.0D)).isEqualTo("100.00");
        assertThat(formatter.floatFormat(0.5D)).isEqualTo("0.50");

        // 정수 표현(소수점은 반올림 처리)
        assertThat(formatter.binaryFormat(0.4D)).isEqualTo("0");
        assertThat(formatter.binaryFormat(0.5D)).isEqualTo("1");
        assertThat(formatter.binaryFormat(1.3D)).isEqualTo("1");
        assertThat(formatter.binaryFormat(2.6D)).isEqualTo("3");
    }

    @Test
    void getASCIICode() {
        String a = "abc";
        assertThat(a.charAt(0)).isEqualTo('a');
        assertThat((short) a.charAt(1)).isEqualTo(Short.parseShort("98"));
        assertThat((byte) a.charAt(2)).isEqualTo(Byte.parseByte("99"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void compareWithCharacter() {
        String a = "A";
        char b = 'A';
        assertThat(a).isNotEqualTo(b);
        assertThat(b).isEqualTo(a.charAt(0));
        assertThat(a).isEqualTo(String.valueOf(b));
    }

    /**
     * 사전 상 순서를 비교하는 메서드 String.compareTo()
     */
    @Test
    void testCompareTo() {
        // 같으면 0
        assertThat("A".compareTo("A")).isEqualTo(0);

        // 비교 대상이 나중이면 -1 (= 좌측이 우선순위가 높으면)
        assertThat("A".compareTo("B")).isEqualTo(-1);

        // 비교 대상이 먼저면 1 (= 우측이 우선순위가 높으면
        assertThat("B".compareTo("A")).isEqualTo(1);

        // 대문자는 소문자보다 빠르고, 대문자에서 소문자까지는 32번만큼의 차이가 있다.
        // 알파벳은 26개인데 왜 32냐면
        // ASCII 코드표에서 대문자(65-90)와 소문자(97-122) 사이에 6개의 특수문자(91-96)가 있기 때문이다
        // ... X Y Z [ | ] ^ _ ` a b c ...
        assertThat("C".compareTo("c")).isEqualTo(-32);

        // 대소문자 비교 거꾸로
        assertThat("c".compareTo("C")).isEqualTo(32);
    }

    @Test
    void testGetBytes() throws UnsupportedEncodingException {
        final String str = "한";
        assertThat(str.getBytes(StandardCharsets.UTF_8)).isEqualTo(new byte[]{-19, -107, -100});
        assertThat(str.getBytes(StandardCharsets.UTF_16)).isEqualTo(new byte[]{-2, -1, -43, 92});
        assertThat(str.getBytes(Charset.forName("EUC-KR"))).isEqualTo(new byte[]{-57, -47});
    }

    @Test
    void testToStringFromBytes() {
        byte[] bytes = {-19, -107, -100};
        String korean = new String(bytes, StandardCharsets.UTF_8);
        assertThat(korean).isEqualTo("한");
    }

    @Test
    void testConcat() {
        String a = "a";
        assertThat(a).isEqualTo("a");
        assertThat(a + "b").isEqualTo("ab");
        assertThat(a + null).isEqualTo("anull");
    }

    @Test
    void autoInstantiate() {
        String a = "a";
        String b = "a";
        assertThat(a).isSameAs(b);
        b = a; // new String(a)처럼 명시적으로 새 인스턴스를 할당하지 않아도.
        b = "b"; // 리터럴이 바뀌면 참조하고 있는 인스턴스의 값이 변경되는게 아니라 새 인스턴스가 할당된다. (String 타입의 특징)
        assertThat(a).isEqualTo("a"); // 그래서 a는 원래의 값을 유지할 수 있다.
    }

    @Test
    void useBuilder() {
        StringBuilder builder = new StringBuilder();
        assertThat(builder.length()).isEqualTo(0);
        assertThat(builder.toString()).isEqualTo("");
    }

    @Test
    void getLengthWithLineDelimiter() {
        String a = "totcnt123\nstart";
        String b = "totcnt123start";
        assertThat(a.indexOf("start")).isEqualTo(10);
        assertThat(a.substring(a.indexOf("start"))).isEqualTo("start");

        assertThat(b.indexOf("start")).isEqualTo(9);
        assertThat(b.substring(b.indexOf("start"))).isEqualTo("start");
    }

    @Test
    void useIntern() {
        // 아래처럼 초기화될 땐 intern()을 쓰든 안쓰든 String의 주소값은 같다.
        String a = "경기";
        String b = "경기";
        assertThat(b).isSameAs(a);

        // 요로케 해야됨
        String c = "AAA";
        String d = new String("AAA");
        assertThat(d).isNotSameAs(c);

        d = d.intern();
        assertThat(d).isSameAs(c);

        assertThat("BBB").isSameAs(new String("BBB").intern());
        assertThat("CCC").isSameAs(new String("CCC").intern());
        assertThat("CCC".hashCode()).isEqualTo(new String("CCC").intern().hashCode());
    }

    /**
     * <p>자바 string.replace()는 자바스크립트의 String.prototype.replace()와 달리 첫 번째 인자로 정규식을 받지 않는다. 문자열만 받으며, 문자열과 일치하는 모든 문자열을 치환한다.</p>
     * <p>처음 발견한 하나만 치환하게 하고 싶으면 {@link String#replaceFirst(String, String)}를 사용할 것</p>
     */
    @Test
    void testReplace() {
        var str = "a'b'c'd'e'f'g";
        String result = str.replace("'", "");
        assertThat(result).isEqualTo("abcdefg");
        assertThat("a b c d e f g".replace(" ", "")).isEqualTo("abcdefg");
    }

    /**
     * string.replaceAll()은 첫 번째 인자로 정규식을 받으며, g 플래그를 사용한 것과 동일하게 작동한다.
     */
    @Test
    void testReplaceAll() {
        assertThat("경기도".replaceAll("도", "")).isEqualTo("경기");
        assertThat("a:b".replaceAll(":", "-")).isEqualTo("a-b");
        assertThat("a:b".replaceAll("\\:", "\\-")).isEqualTo("a-b");

        var str = "Duplicate entry 'foo' for key 'bar'";
        String result = str.replaceAll("'", "");
        assertThat(result).isEqualTo("Duplicate entry foo for key bar");
    }

    /**
     * string.replaceFirst()도 첫 번째 인자로 정규식을 받는다. 처음으로 검색된 패턴만 치환한다.
     */
    @Test
    void testReplaceFirst() {
        String str = "/qwe/test/submit/sender";
        assertThat(str.replaceFirst("/qwe", "")).isEqualTo("/test/submit/sender");
    }

    @Test
    void splitDomain() {
        assertThat("localhost".split("\\.").length).isEqualTo(1);
        assertThat("master.benecafe.com".split("\\.").length).isEqualTo(3);
        assertThat("daum.net".split("\\.").length).isEqualTo(2);
    }

    @Test
    void testSubstring() {
        String str = "a1234567890b234567890c234567890d23";
        assertThat(str.substring(0, 1)).isEqualTo("a");
        assertThat(str.substring(0, 6)).isEqualTo("a12345");
        assertThat(str.substring(4, 7)).isEqualTo("456");
        assertThat(str.length()).isEqualTo(34);
        assertThat(str.substring(0, 30)).isEqualTo("a1234567890b234567890c23456789");
        assertThat(str.substring(0, 30).length()).isEqualTo(30);
        assertThat("8804127".substring(4, 5)).isEqualTo("1");
        assertThat("8804127".substring(5, 6)).isEqualTo("2");
        assertThat("8804127".substring(6, 7)).isEqualTo("7");

        assertThat(str.substring(0, str.indexOf('5'))).isEqualTo("a1234");
    }

    @Test
    void testIndexOf() {
        String a = "INFO  log4jdbc.log4j2 - 5. ResultSet.close() returned void";
        assertThat(a.indexOf('N')).isEqualTo(1);
        assertThat(a.indexOf("NFO")).isEqualTo(1);
        assertThat(a.indexOf("ResultSet.")).isEqualTo(27); // 첫 번째 "ResultSet."
        assertThat(a.indexOf('.')).isEqualTo(14); // 첫 번째 "."
        assertThat(a.indexOf('.', 15)).isEqualTo(25); // 인덱스 15 이후부터 찾음

        assertThat(a.lastIndexOf('.')).isEqualTo(36); // 마지막 "."
        assertThat(a.lastIndexOf('.', 35)).isEqualTo(25); // 인덱스 35 이전부터 찾음
    }

    @Test
    void testLastIndexOf() {
        String str = "/ab/cd";
        assertThat(str.lastIndexOf('/')).isEqualTo(3);
        assertThat(str.substring(str.lastIndexOf('/') + 1)).isEqualTo("cd");
    }

    @Test
    void testSplit() {
        String splitMe = "abcdefghijklmn";
        assertThat(splitMe.split("\\|")[0]).isEqualTo(splitMe);

        assertThatThrownBy(() -> {
            String arr = splitMe.split("\\|")[1];
        }).isInstanceOf(IndexOutOfBoundsException.class);

        String splitMe2 = "abcdefghijklmn";
        assertThat(splitMe2.length()).isEqualTo(14);
        assertThat(splitMe2.subSequence(1, 5)).isEqualTo(splitMe2.substring(1, 5));

        String splitMe3 = "a\nb\nc\nd";
        assertThat(splitMe3.split("\\n").length).isEqualTo(4);

        String splitMe4 = "";
        String[] splited = splitMe4.split("\\|");
        assertThat(splited[0]).isEqualTo("");
    }

    @Test
    void splitByLength1333() {
        String str = "00x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 1
        str += "01x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 2
        str += "02x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 3
        str += "03x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 4
        str += "04x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 5
        str += "05x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 6
        str += "06x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 7
        str += "07x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 8
        str += "08x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 9
        str += "09x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 10
        str += "10x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 11
        str += "11x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 12
        str += "12x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 13
        str += "13x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"; // 14
        str += "14x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "15x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "16x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "17x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "18x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "19x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "20x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "21x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "22x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "23x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "24x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "25x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "26x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "27x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "28x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "29x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "30x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
        str += "31x1111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";

//		int len = str.length();

        String[] strArray = splitByLength1333(str);
//		for (String ele : strArray) {
//			log.debug(ele);
//		}
        assertThat(splitByLength(str, 1333)).isEqualTo(strArray);
    }

    @Test
    void testSplitByLength() {
        assertThat(splitByLength("abcdef", 3)).isEqualTo(new String[]{"abc", "def"});
        assertThat(splitByLength("abcdefef", 3)).isEqualTo(new String[]{"abc", "def", "ef"});
        assertThat(splitByLength("abcdef1234", 4)).isEqualTo(new String[]{"abcd", "ef12", "34"});
        assertThat(splitByLength("abcdef12345", 5)).isEqualTo(new String[]{"abcde", "f1234", "5"});
    }

    static String[] splitByLength1333(String str) {
        return splitByLength(str, 1333);
    }

    static String[] splitByLength(String str, int splitLength) {
        if (null == str) {
            return null;
        }

        int strLen = str.length();
        int arrayLength = Math.abs(strLen / splitLength) + (0 != strLen % splitLength ? 1 : 0);

        String[] strArray = null;
        if (0 == arrayLength) {
            return new String[]{str};
        } else {
            strArray = new String[arrayLength];
        }

        String temp = "";
        for (int i = 0; i < arrayLength; i++) {
            if (str.length() > splitLength) {
                strArray[i] = str.substring(0, splitLength);
                temp = str.substring(splitLength);
            } else {
                strArray[i] = str;
            }
            str = temp;
        }
        return strArray;
    }

    @Test
    void testJoin() {
        List<String> texts = Arrays.asList("a", "b", "c");
        assertThat(String.join(", ", texts)).isEqualTo("a, b, c");
    }

    @Test
    void testIsEmpty() {
        assertThat("".isEmpty()).isTrue();
        assertThat(" ".isEmpty()).isFalse();
        assertThat("  ".isEmpty()).isFalse();
        assertThat("qwer".isEmpty()).isFalse();

        assertThatThrownBy(() -> {
            String str = null;
            str.isEmpty();
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testIsBlank() {
        assertThat("".isBlank()).isTrue();
        assertThat(" ".isBlank()).isTrue();
        assertThat("  ".isBlank()).isTrue();
        assertThat("qwer".isBlank()).isFalse();
        assertThatThrownBy(() -> {
            String str = null;
            str.isBlank();
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testStartWith() {
        String target = "/at/adm/member/member";
        assertThat(target.startsWith("/at/adm")).isTrue();
    }

    // java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '11' for key 'beacon.uuid'
    @Test
    void extractColumnFromSqlException() {
        String errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '11' for key 'beacon.uuid'";
        String[] s1 = errorMessage.split("Duplicate entry '");
        String[] s2 = s1[1].split("' for key '");
        String value = s2[0];
        String column = s2[1].replaceAll("'", "");

        assertThat(value).isEqualTo("11");
        assertThat(column).isEqualTo("beacon.uuid");
    }

    /**
     * 케릭터셋(인코딩, encoding) 변경하기
     */
    @Test
    void convertCharacterSet() {
        final String text = "12 ab 가나";
        log.debug("text: {}", text);

        byte[] bytes1 = text.getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = text.getBytes(Charset.forName("EUC-KR"));

        // 사실 이건 엉터리 소스다.
        String converted = new String(bytes1, Charset.forName("EUC-KR"));
        assertThat(converted).isNotEqualTo(text);
        log.debug("converted: {}", converted); // 12 ab 媛���

        assertThat(bytes2).isNotEqualTo(bytes1);
        log.debug("bytes1: {}", bytes1); // [49, 50, 32, 97, 98, 32, -22, -80, -128, -21, -126, -104]
        log.debug("bytes2: {}", bytes2); // [49, 50, 32, 97, 98, 32, -80, -95, -77, -86]
    }
}
