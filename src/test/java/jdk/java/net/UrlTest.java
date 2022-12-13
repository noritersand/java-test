package jdk.java.net;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * URL을 다루기 위한 타입 테스트. URL은 URI보다 하위 개념이며 인터넷 자원을 의미한다.
 *
 * @author fixalot
 * @since 2017-08-21
 */
public class UrlTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UrlTest.class);

    @Test
    public void initialize() throws MalformedURLException {
        URL url = new URL("http://google.com/someone?a=야");
        assertEquals("http://google.com/someone?a=야", url.toString());
    }

    @Test
    public void getSpecificInformation() throws IOException {
        URL url = new URL("http://google.com/someone?a=야");
        assertEquals("http", url.getProtocol());
        assertEquals("google.com", url.getHost());
        assertEquals("/someone", url.getPath());
        assertEquals("a=야", url.getQuery());

        assertEquals(-1, url.getPort());
        assertEquals(80, url.getDefaultPort());

        assertEquals("google.com", url.getAuthority());
        assertEquals(null, url.getUserInfo());
        assertEquals(null, url.getRef());
    }
}
