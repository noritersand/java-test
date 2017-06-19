package laboratory.jdk.java.nio;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharsetTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CharsetTest.class);
	
	@Test
	public void testCharset() {
		Assert.assertTrue(Charset.isSupported("UTF-8"));
		
//		Charset charset = Charset.defaultCharset(); // default는 의도하지 않은 결과가 나올 수 있음.
		Charset charset = Charset.forName("UTF-8");
		Assert.assertTrue(charset.canEncode());
		Assert.assertEquals("UTF-8", charset.toString());
		Assert.assertEquals("UTF-8", charset.displayName(Locale.KOREA));
		
		charset = Charset.forName("EUC-KR");
		Assert.assertTrue(charset.canEncode());
		Assert.assertEquals("EUC-KR", charset.toString());
		Assert.assertEquals("EUC-KR", charset.displayName(Locale.KOREA));
	}
	
	@Test
	public void getStandardCharsetName() {
		Assert.assertEquals("US-ASCII", StandardCharsets.US_ASCII.name());
		Assert.assertEquals("ISO-8859-1", StandardCharsets.ISO_8859_1.name());
		Assert.assertEquals("UTF-8", StandardCharsets.UTF_8.name());
		Assert.assertEquals("UTF-16", StandardCharsets.UTF_16.name());
	}
}
