package jdk.java.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * java.util.StringTokenizer 테스트
 *
 * @author fixalot
 * @since 2017-12-20
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
