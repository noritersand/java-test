package laboratory.test.java.nio;

import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharsetTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CharsetTest.class);
	
	@Test
	public void test() {
		Assert.assertTrue(Charset.isSupported("UTF-8"));
		
		Charset charset = Charset.defaultCharset();
		Assert.assertTrue(charset.canEncode());
		Assert.assertEquals("UTF-8", charset.toString());
		Assert.assertEquals("UTF-8", charset.displayName(Locale.KOREA));
		
		charset = Charset.forName("EUC-KR");
		Assert.assertTrue(charset.canEncode());
		Assert.assertEquals("EUC-KR", charset.toString());
		Assert.assertEquals("EUC-KR", charset.displayName(Locale.KOREA));
	}
}
