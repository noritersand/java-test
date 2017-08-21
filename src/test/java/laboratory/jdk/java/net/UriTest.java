package laboratory.jdk.java.net;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * URI를 다루기 위한 타입 테스트.
 * URI는 URL보다 상위 개념이며 인터넷 자원 뿐만이 아니라 컴퓨터 상의 모든 자원을 특정한다.
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class UriTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UriTest.class);
	
	@Test
	public void create() {
		URI uri = URI.create("somefolder/somefile.png");
		Assert.assertEquals("somefolder/somefile.png", uri.toString());
		Assert.assertEquals(null, uri.getScheme());

		URI uri2 = URI.create("file://somefolder/somefile.png");
		Assert.assertEquals("file://somefolder/somefile.png", uri2.toString());
		Assert.assertEquals("file", uri2.getScheme());
		
		URI uri3 = URI.create("http://google.com/someone?a=야");
		Assert.assertEquals("http://google.com/someone?a=야", uri3.toString());
		Assert.assertEquals("http", uri3.getScheme());
	}
	
	@Test
	public void getSpecificInformation() {
		URI uri = URI.create("http://google.com/someone?a=야");

		Assert.assertEquals("http", uri.getScheme());

		Assert.assertEquals("google.com", uri.getAuthority());
		Assert.assertEquals("google.com", uri.getRawAuthority());

		Assert.assertEquals(null, uri.getFragment());
		Assert.assertEquals(null, uri.getRawFragment());

		Assert.assertEquals("/someone", uri.getPath());
		Assert.assertEquals("/someone", uri.getRawPath());

		Assert.assertEquals("a=야", uri.getQuery());
		Assert.assertEquals("a=야", uri.getRawQuery());

		Assert.assertEquals(-1, uri.getPort());

		Assert.assertEquals("//google.com/someone?a=야", uri.getRawSchemeSpecificPart());

		Assert.assertEquals(null, uri.getRawUserInfo());
	}
}
