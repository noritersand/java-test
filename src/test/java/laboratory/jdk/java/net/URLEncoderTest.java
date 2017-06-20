package laboratory.jdk.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLEncoderTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(URLEncoderTest.class);
	
	@Test
	public void encodeUrl() throws UnsupportedEncodingException {
		final String utf8 = "UTF-8";
		Assert.assertEquals("http%3A%2F%2Fgoogle.com%3Fq%3D%ED%95%9C%EA%B8%80", URLEncoder.encode("http://google.com?q=한글", utf8));
		Assert.assertEquals("%3Fa%3D1%26b%3D2", URLEncoder.encode("?a=1&b=2", utf8));
	}
}
