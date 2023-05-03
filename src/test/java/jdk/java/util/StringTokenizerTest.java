package jdk.java.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * java.util.StringTokenizer 테스트
 *
 * @author fixalot
 * @since 2017-12-20
 */
@Slf4j
public class StringTokenizerTest {

    @Test
    void test() {
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
