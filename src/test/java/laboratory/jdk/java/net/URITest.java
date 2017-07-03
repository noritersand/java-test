package laboratory.jdk.java.net;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;

public class URITest {
	@Test
	public void getURI() {
		URI uri3 = URI.create("somefolder/somefile.png");
		System.out.println(uri3);
		Assert.assertEquals(null, uri3.getScheme());

		URI uri2 = URI.create("file://somefolder/somefile.png");
		System.out.println(uri2);
		Assert.assertEquals("file", uri2.getScheme());

		URI uri = URI.create("http://google.com/someone?a=야");
		System.out.println(uri);

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
