package laboratory.jdk18.java.util;

import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java.util.StringTokenizer 테스트 유닛
 * 
 * @since 2017-12-20
 * @author fixalot
 */
public class StringTokenizerTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StringTokenizerTest.class);
	
	@Test
	public void test() {
		final String text = "a,b|c,d";
		
		StringTokenizer token1 = new StringTokenizer(text, ",");
		StringTokenizer token2 = new StringTokenizer(text, "|");
		
//		token1.hasMoreElements(); // 내부에서 hasMoreTokens()를 호출함
		Assert.assertTrue(token1.hasMoreTokens());
		Assert.assertEquals("a", token1.nextToken());
		Assert.assertEquals("b|c", token1.nextToken());
		Assert.assertEquals("d", token1.nextToken());
		Assert.assertFalse(token1.hasMoreTokens());
		
		Assert.assertTrue(token2.hasMoreTokens());
		Assert.assertEquals("a,b", token2.nextToken());
		Assert.assertEquals("c,d", token2.nextToken());
		Assert.assertFalse(token2.hasMoreTokens());
	}
	
}
