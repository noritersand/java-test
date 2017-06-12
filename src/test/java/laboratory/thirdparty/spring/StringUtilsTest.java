package laboratory.thirdparty.spring;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class StringUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(StringUtilsTest.class);
	
	@Test
	public void tokenize() {
		final String str = "first, second;third fourth\tfifth\nsixth";
		String[] tokens = StringUtils.tokenizeToStringArray(str, ",; \t\n"); // 쉼표, 세미콜론, 빈칸, 탭, 줄바꿈 중 아무거나 있으면 별도 토큰으로 인식해서 쪼갬
		Assert.assertEquals("[first, second, third, fourth, fifth, sixth]", Arrays.toString(tokens));
	}
}
