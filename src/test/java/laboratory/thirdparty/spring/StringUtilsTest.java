package laboratory.thirdparty.spring;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class StringUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StringUtilsTest.class);

	@Test
	public void testTokenize() {
		final String string = "first, second;third fourth\tfifth\nsixth";
		String[] tokens = StringUtils.tokenizeToStringArray(string, ",; \t\n"); // 쉼표, 세미콜론, 빈칸, 탭, 줄바꿈 중 아무거나 있으면 별도 토큰으로 인식해서 쪼갬
		Assert.assertTrue(Arrays.equals(new String[] { "first", "second", "third", "fourth", "fifth", "sixth" }, tokens));

		// delimeter가 연속으로 사용되도 하나로 빈문자열은 쪼개지 않음
		final String anotherString = "first,;second";
		String[] tokens2 = StringUtils.tokenizeToStringArray(anotherString, ",;");
		Assert.assertEquals("first", tokens2[0]);
		Assert.assertEquals("second", tokens2[1]);

		// 자동으로 trim까지 해줌. 짱짱맨
		final String splitemetrimme = " first\t , second ";
		String[] tokens3 = StringUtils.tokenizeToStringArray(splitemetrimme, ",");
		Assert.assertEquals("first", tokens3[0]);
		Assert.assertEquals("second", tokens3[1]);
	}
}
