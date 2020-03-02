package jdk.java.util.regex;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String과 함께하는 정규식 뿅뿅 파티! 
 * 
 * @since 2020-03-02
 * @author noritersand
 */
public class RegexWithStringTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RegexWithStringTest.class);

	@Test
	public void shoulBeEquals() {
		Assert.assertEquals("AA1062329819", removeTail("AA1062329819_1"));
		Assert.assertEquals("AA1062329819", removeTail("AA1062329819_199A"));

		Assert.assertEquals("1", extractTail("AA1062329819_1"));
		Assert.assertEquals("199A", extractTail("AA1062329819_199A"));

		Assert.assertEquals("1", extractTailUsingApacheCommons("AA1062329819_1"));
		Assert.assertEquals("12A3", extractTailUsingApacheCommons("AA1062329819_12A3"));
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