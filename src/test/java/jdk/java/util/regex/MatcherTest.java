package jdk.java.util.regex;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java.util.regex.Matcher 테스트 유닛
 * 
 * @since 2019-02-26
 * @author fixalot
 */
public class MatcherTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MatcherTest.class);

	/**
	 * 주어진 문자열의 리터럴 대체 문자열(??? regex 패턴 문자열인가...)을 반환한다.  
	 * 
	 * @author fixalot
	 */
	@Test
	public void testQuoteReplacement() {
		Assert.assertEquals("\\\\", Matcher.quoteReplacement("\\"));
		Assert.assertEquals("	", Matcher.quoteReplacement("\t"));
		Assert.assertEquals("'", Matcher.quoteReplacement("\'"));
	}

	@Test
	public void usingWithStringReplace() {
		String str = "a/b/c/d";
		str = str.replaceAll("/", Matcher.quoteReplacement("\\"));
		Assert.assertEquals("a\\b\\c\\d", str);
		
		// 반대로도 됨
		String str2 = "a\\b\\c\\d";
		str2 = str2.replaceAll(Matcher.quoteReplacement("\\"), "/");
		Assert.assertEquals("a/b/c/d", str2);
	}
}
