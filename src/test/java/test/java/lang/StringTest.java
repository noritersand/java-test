package test.java.lang;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(StringTest.class);

	@Test
	public void testIntern() {
		// 아래처럼 초기화될 땐 intern()을 쓰든 안쓰든 String의 주소값은  같다.
		String a = "경기";
		String b = "경기";
		Assert.assertTrue(a == b);
		
		// 요로케 해야됨
	    String c = "AAA";
	    String d = new String("AAA");
	    Assert.assertFalse(c == d);

	    d = d.intern();
	    Assert.assertTrue(c == d);
	    
//	    Assert.assertTrue(new String("BBB").intern() == "BBB"); // 이 줄처럼 리터럴이 나중에 올땐 실패할 수도 있다.
	}
	
	@Test
	public void testReplaceAlls() {
		Assert.assertEquals("경기", "경기도".replaceAll("도", ""));
		Assert.assertEquals("전라", "전라도".replaceAll("도", ""));
		Assert.assertEquals("경상", "경상도".replaceAll("도", ""));
	}
	
	@Test
	public void testDomainSplit() {
		Assert.assertEquals(1, "localhost".split("\\.").length);
		Assert.assertEquals(3, "master.benecafe.com".split("\\.").length);
		Assert.assertEquals(2, "daum.net".split("\\.").length);
	}

	@Test
	public void testSubstring() {
		String str = "a234567890b234567890c234567890d234";
		Assert.assertEquals(34, str.length());
		Assert.assertEquals("a234567890b234567890c234567890", str.substring(0, 30));
		Assert.assertEquals(30, str.substring(0, 30).length());
	}

	@Test
	public void testLastIndexOf() {
		String str = "/abcd";
		Assert.assertEquals(0, str.lastIndexOf("/"));
		Assert.assertEquals("abcd", str.substring(str.lastIndexOf("/") + 1));
	}

	@Test
	public void testSplit() {
		String str = "abcdefghijklmn";
		Assert.assertEquals(str.substring(1, 5), str.subSequence(1, 5));
	}

	@Test
	public void testSplit2() {
		String str = "abcdefghijklmn";
		Assert.assertEquals(14, str.length());
		Assert.assertEquals(str.substring(1, 5), str.subSequence(1, 5));
	}

	@Test
	public void testSplitByLength1333() {
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
		Assert.assertArrayEquals(strArray, splitByLength(str, 1333));
	}

	@Test
	public void testAbs() {
		Assert.assertEquals(0, Math.abs(1000/1333));
		Assert.assertEquals(1, Math.abs(1333/1333));
		Assert.assertEquals(1, Math.abs(1334/1333));
		Assert.assertEquals(1, Math.abs(2665/1333));
		Assert.assertEquals(2, Math.abs(2666/1333));
		Assert.assertEquals(2, Math.abs(2667/1333));
	}

	@Test
	public void testSplitByLength() {
		Assert.assertArrayEquals(new String[] { "abc", "def" }, splitByLength("abcdef", 3));
		Assert.assertArrayEquals(new String[] { "abc", "def", "ef" }, splitByLength("abcdefef", 3));
		Assert.assertArrayEquals(new String[] { "abcd", "ef12", "34" }, splitByLength("abcdef1234", 4));
		Assert.assertArrayEquals(new String[] { "abcde", "f1234", "5" }, splitByLength("abcdef12345", 5));
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
}

