package jdk.java.lang;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class StringTest {
	private static final Logger logger = LoggerFactory.getLogger(StringTest.class);
	
	@Test
	public void testIndexOf() {
		String a = "INFO  log4jdbc.log4j2 - 5. ResultSet.close() returned void";
		assertEquals(0, a.indexOf("I"));
		assertEquals(27, a.indexOf("ResultSet.")); // 첫 번째 "ResultSet."
		assertEquals(14, a.indexOf(".")); // 첫 번째 "."
		assertEquals(25, a.indexOf(".", 15)); // 인덱스 15 이후부터 찾음
		
		assertEquals(36, a.lastIndexOf(".")); // 마지막 "."
		assertEquals(25, a.lastIndexOf(".", 35)); // 인덱스 35 이전부터 찾음
	}

	@Test
	public void testFormat() {
        class Formatter {
            public String floatFormat(double number) {
				return String.format("%.2f", number); // dot(.)과 바로 뒤에오는 숫자는 정밀도를 의미한다.
            }
            public String binaryFormat(double number) {
            	return String.format("%.0f", number);
            }
        }
        Formatter formatter = new Formatter();
		
        // 소수점 2자리까지  표현
        assertEquals("1.00", formatter.floatFormat(1D));
		assertEquals("100.00", formatter.floatFormat(100D));
		assertEquals("0.50", formatter.floatFormat(0.5D));
		
		// 정수 표현(소수점은 반올림 처리)
		assertEquals("0", formatter.binaryFormat(0.4D));
		assertEquals("1", formatter.binaryFormat(0.5D));
		assertEquals("1", formatter.binaryFormat(1.3D));
		assertEquals("3", formatter.binaryFormat(2.6D));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void compareWithCharacter() {
		String a = "A";
		char b = 'A';
		assertFalse(a.equals(b));
		assertTrue(a.charAt(0) == b);
		assertTrue(a.equals(String.valueOf(b)));
	}

	@Test
	public void testGetBytes() throws UnsupportedEncodingException {
		final String str = "한";
		assertArrayEquals(new byte[] { -19, -107, -100 }, str.getBytes(StandardCharsets.UTF_8));
		assertArrayEquals(new byte[] { -2, -1, -43, 92 }, str.getBytes(StandardCharsets.UTF_16));
		assertArrayEquals(new byte[] { -57, -47 }, str.getBytes(Charset.forName("EUC-KR")));
	}

	@Test
	public void testToStringFromBytes() {
		byte[] bytes = new byte[] { -19, -107, -100 };
		String korean = new String(bytes, StandardCharsets.UTF_8);
		assertEquals("한", korean);
	}

	@Test
	public void testConcat() {
		String a = "a";
		assertEquals("ab", a.concat("b"));
		assertEquals("a", a);
		try {
			assertEquals("anull", a.concat(null)); // NPE
		} catch (Exception e) {
		}
		assertEquals("anull", a + null);
	}

	@Test
	public void autoInstantiate() {
		String a = "a";
		String b = "a";
		assertTrue(a == b);
		b = a; // new String(a)처럼 명시적으로 새 인스턴스를 할당하지 않아도.
		b = "b"; // 리터럴이 바뀌면 참조하고 있는 인스턴스의 값이 변경되는게 아니라 새 인스턴스가 할당된다. (String 타입의 특징)
		assertEquals("a", a); // 그래서 a는 원래의 값을 유지할 수 있다.
	}

	@Test
	public void useBuilder() {
		StringBuilder builder = new StringBuilder();
		assertEquals(0, builder.length());
		assertEquals("", builder.toString());
	}

	@Test
	public void getLengthWithLineDelimiter() {
		String a = "totcnt123\nstart";
		String b = "totcnt123start";
		assertEquals(10, a.indexOf("start"));
		assertEquals("start", a.substring(a.indexOf("start"), a.length()));

		assertEquals(9, b.indexOf("start"));
		assertEquals("start", b.substring(b.indexOf("start"), b.length()));
	}

	@Test
	public void useIntern() {
		// 아래처럼 초기화될 땐 intern()을 쓰든 안쓰든 String의 주소값은 같다.
		String a = "경기";
		String b = "경기";
		assertTrue(a == b);

		// 요로케 해야됨
		String c = "AAA";
		String d = new String("AAA");
		assertFalse(c == d);

		d = d.intern();
		assertTrue(c == d);

		assertTrue(new String("BBB").intern() == "BBB");
		assertTrue(new String("CCC").intern() == "CCC");
		assertEquals(new String("CCC").intern().hashCode(), "CCC".hashCode());
	}

	@Test
	public void testReplaceAll() {
		assertEquals("경기", "경기도".replaceAll("도", ""));
		assertEquals("a-b", "a:b".replaceAll(":", "-"));
		assertEquals("a-b", "a:b".replaceAll("\\:", "\\-"));
	}

	@Test
	public void testReplaceFirst() {
		String str = "/qwe/test/submit/sender";
		assertEquals("/test/submit/sender", str.replaceFirst("/qwe", ""));
	}

	@Test
	public void splitDomain() {
		assertEquals(1, "localhost".split("\\.").length);
		assertEquals(3, "master.benecafe.com".split("\\.").length);
		assertEquals(2, "daum.net".split("\\.").length);
	}

	@Test
	public void testSubstring() {
		String str = "a1234567890b234567890c234567890d23";
		assertEquals("a", str.substring(0, 1));
		assertEquals("a12345", str.substring(0, 6));
		assertEquals("456", str.substring(4, 7));
		assertEquals(34, str.length());
		assertEquals("a1234567890b234567890c23456789", str.substring(0, 30));
		assertEquals(30, str.substring(0, 30).length());
	}

	@Test
	public void testLastIndexOf() {
		String str = "/abcd";
		assertEquals(0, str.lastIndexOf("/"));
		assertEquals("abcd", str.substring(str.lastIndexOf("/") + 1));
	}

	@Test
	public void testSplit() {
		String splitMe = "abcdefghijklmn";
		assertEquals(splitMe, splitMe.split("\\|")[0]);
		try {
			logger.debug(splitMe.split("\\|")[1]);
		} catch (IndexOutOfBoundsException e) {
			logger.debug("에러 났지롱");
		}

		String splitMe2 = "abcdefghijklmn";
		assertEquals(14, splitMe2.length());
		assertEquals(splitMe2.substring(1, 5), splitMe2.subSequence(1, 5));

		String splitMe3 = "a\nb\nc\nd";
		assertEquals(4, splitMe3.split("\\n").length);
		
		String splitMe4 = "";
		String[] splited = splitMe4.split("\\|");
		assertEquals("", splited[0]);
	}

	@Test
	public void splitByLength1333() {
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
//			logger.debug(ele);
//		}
		assertArrayEquals(strArray, splitByLength(str, 1333));
	}

	@Test
	public void testSplitByLength() {
		assertArrayEquals(new String[] { "abc", "def" }, splitByLength("abcdef", 3));
		assertArrayEquals(new String[] { "abc", "def", "ef" }, splitByLength("abcdefef", 3));
		assertArrayEquals(new String[] { "abcd", "ef12", "34" }, splitByLength("abcdef1234", 4));
		assertArrayEquals(new String[] { "abcde", "f1234", "5" }, splitByLength("abcdef12345", 5));
	}

	public static String[] splitByLength1333(String str) {
		return splitByLength(str, 1333);
	}

	public static String[] splitByLength(String str, int splitLength) {
		if (str == null) {
			return null;
		}

		int strLen = str.length();
		int arrayLength = Math.abs(strLen / splitLength) + (strLen % splitLength != 0 ? 1 : 0);

		String[] strArray = null;
		if (arrayLength == 0) {
			return new String[] { str };
		} else {
			strArray = new String[arrayLength];
		}

		String temp = "";
		for (int i = 0; i < arrayLength; i++) {
			if (str.length() > splitLength) {
				strArray[i] = str.substring(0, splitLength);
				temp = str.substring(splitLength, str.length());
			} else {
				strArray[i] = str.substring(0, str.length());
			}
			str = temp;
		}
		return strArray;
	}
	
	@Test
	public void testJoin() {
		List<String> texts = Arrays.asList("a", "b", "c");
		assertEquals("a, b, c", String.join(", ", texts));
	}
}
