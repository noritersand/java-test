package builtin.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * URI를 다루기 위한 타입 테스트.
 * URI는 URL보다 상위 개념이며 인터넷 자원 뿐만이 아니라 컴퓨터 상의 모든 자원을 특정한다.
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class UriTest {

    @Test
    void initialize() {
        URI uri = URI.create("somefolder/somefile.png");
        assertEquals("somefolder/somefile.png", uri.toString());
        assertNull(uri.getScheme());

        URI uri2 = URI.create("file://somefolder/somefile.png");
        assertEquals("file://somefolder/somefile.png", uri2.toString());
        assertEquals("file", uri2.getScheme());

        URI uri3 = URI.create("http://google.com/someone?a=야");
        assertEquals("http://google.com/someone?a=야", uri3.toString());
        assertEquals("http", uri3.getScheme());
    }

    @Test
    void getSpecificInformation() {
        URI uri = URI.create("http://google.com/someone?a=야");

        assertEquals("http", uri.getScheme());

        assertEquals("google.com", uri.getAuthority());
        assertEquals("google.com", uri.getRawAuthority());

        assertNull(uri.getFragment());
        assertNull(uri.getRawFragment());

        assertEquals("/someone", uri.getPath());
        assertEquals("/someone", uri.getRawPath());

        assertEquals("a=야", uri.getQuery());
        assertEquals("a=야", uri.getRawQuery());

        assertEquals(-1, uri.getPort());

        assertEquals("//google.com/someone?a=야", uri.getRawSchemeSpecificPart());

        assertNull(uri.getRawUserInfo());
    }
}
