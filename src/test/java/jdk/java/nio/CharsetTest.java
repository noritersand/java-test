package jdk.java.nio;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class CharsetTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CharsetTest.class);

    @Test
    public void useCharset() {
        assertTrue(Charset.isSupported("UTF-8"));

//		Charset charset = Charset.defaultCharset(); // default는 의도하지 않은 결과가 나올 수 있음.
        Charset charset = Charset.forName("UTF-8");
        assertTrue(charset.canEncode());
        assertEquals("UTF-8", charset.toString());
        assertEquals("UTF-8", charset.displayName(Locale.KOREA));

        charset = Charset.forName("EUC-KR");
        assertTrue(charset.canEncode());
        assertEquals("EUC-KR", charset.toString());
        assertEquals("EUC-KR", charset.displayName(Locale.KOREA));
    }

    @Test
    public void useStandardCharset() {
        assertEquals("US-ASCII", StandardCharsets.US_ASCII.name());
        assertEquals("ISO-8859-1", StandardCharsets.ISO_8859_1.name());
        assertEquals("UTF-8", StandardCharsets.UTF_8.name());
        assertEquals("UTF-16", StandardCharsets.UTF_16.name());
    }
}
