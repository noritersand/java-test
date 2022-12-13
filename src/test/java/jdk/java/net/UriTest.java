package jdk.java.net;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * URI를 다루기 위한 타입 테스트.
 * URI는 URL보다 상위 개념이며 인터넷 자원 뿐만이 아니라 컴퓨터 상의 모든 자원을 특정한다.
 *
 * @author fixalot
 * @since 2017-07-27
 */
public class UriTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UriTest.class);

    @Test
    public void initialize() {
        URI uri = URI.create("somefolder/somefile.png");
        assertEquals("somefolder/somefile.png", uri.toString());
        assertEquals(null, uri.getScheme());

        URI uri2 = URI.create("file://somefolder/somefile.png");
        assertEquals("file://somefolder/somefile.png", uri2.toString());
        assertEquals("file", uri2.getScheme());

        URI uri3 = URI.create("http://google.com/someone?a=야");
        assertEquals("http://google.com/someone?a=야", uri3.toString());
        assertEquals("http", uri3.getScheme());
    }

    @Test
    public void getSpecificInformation() {
        URI uri = URI.create("http://google.com/someone?a=야");

        assertEquals("http", uri.getScheme());

        assertEquals("google.com", uri.getAuthority());
        assertEquals("google.com", uri.getRawAuthority());

        assertEquals(null, uri.getFragment());
        assertEquals(null, uri.getRawFragment());

        assertEquals("/someone", uri.getPath());
        assertEquals("/someone", uri.getRawPath());

        assertEquals("a=야", uri.getQuery());
        assertEquals("a=야", uri.getRawQuery());

        assertEquals(-1, uri.getPort());

        assertEquals("//google.com/someone?a=야", uri.getRawSchemeSpecificPart());

        assertEquals(null, uri.getRawUserInfo());
    }
}
