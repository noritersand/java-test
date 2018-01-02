package thirdparty.apache.commons;

import org.apache.commons.lang3.StringUtils;
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
public class StringUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StringUtilsTest.class);

	@Test
	public void testDefault() {
		Assert.assertEquals("", StringUtils.defaultString("", "0"));
		Assert.assertEquals("0", StringUtils.defaultIfBlank("", "0"));
	}

	@Test
	public void testPadding() {
		Assert.assertEquals("001", StringUtils.leftPad("1", 3, "0"));
		Assert.assertEquals("10000", StringUtils.rightPad("1", 5, "0"));
	}
}
