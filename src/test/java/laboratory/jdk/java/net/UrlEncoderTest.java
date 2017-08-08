package laboratory.jdk.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class UrlEncoderTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UrlEncoderTest.class);

	@Test
	public void encode() throws UnsupportedEncodingException {
		Assert.assertEquals("http%3A%2F%2Fgoogle.com%3Fq%3D%ED%95%9C%EA%B8%80",
				URLEncoder.encode("http://google.com?q=한글", StandardCharsets.UTF_8.toString()));
		Assert.assertEquals("%3Fa%3D1%26b%3D2", URLEncoder.encode("?a=1&b=2", StandardCharsets.UTF_8.toString()));
	}

	@Test
	public void decode() throws UnsupportedEncodingException {
		final String decodeMe = "key=%3Cvalue%3E";
		Assert.assertEquals("key=<value>", URLDecoder.decode(decodeMe, StandardCharsets.UTF_8.toString()));
	}
}
