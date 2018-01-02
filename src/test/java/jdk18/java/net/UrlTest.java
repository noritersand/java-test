package jdk18.java.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * URL을 다루기 위한 타입 테스트.
 * URL은 URI보다 하위 개념이며 인터넷 자원을 의미한다.
 * 
 * @since 2017-08-21
 * @author fixalot
 */
public class UrlTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UrlTest.class);
	
	@Test
	public void newInstance() throws MalformedURLException {
		URL url = new URL("http://google.com/someone?a=야");
		Assert.assertEquals("http://google.com/someone?a=야", url.toString());
	}
	
	@Test
	public void getSpecificInformation() throws IOException {
		URL url = new URL("http://google.com/someone?a=야");
		Assert.assertEquals("http", url.getProtocol());
		Assert.assertEquals("google.com", url.getHost());
		Assert.assertEquals("/someone", url.getPath());
		Assert.assertEquals("a=야", url.getQuery());
		
		Assert.assertEquals(-1, url.getPort());
		Assert.assertEquals(80, url.getDefaultPort());
		
		Assert.assertEquals("google.com", url.getAuthority());
		Assert.assertEquals(null, url.getUserInfo());
		Assert.assertEquals(null, url.getRef());
	}
}
