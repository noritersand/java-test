package jdk.java.util;

import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java.util.StringTokenizer 테스트
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
		assertTrue(token1.hasMoreTokens());
		assertEquals("a", token1.nextToken());
		assertEquals("b|c", token1.nextToken());
		assertEquals("d", token1.nextToken());
		assertFalse(token1.hasMoreTokens());
		
		assertTrue(token2.hasMoreTokens());
		assertEquals("a,b", token2.nextToken());
		assertEquals("c,d", token2.nextToken());
		assertFalse(token2.hasMoreTokens());
	}
	
}
