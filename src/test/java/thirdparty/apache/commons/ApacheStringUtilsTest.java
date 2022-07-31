package thirdparty.apache.commons;

import org.apache.commons.lang3.StringUtils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class ApacheStringUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApacheStringUtilsTest.class);

	@Test
	public void testDefault() {
		assertEquals("", StringUtils.defaultString("", "0"));
		assertEquals("0", StringUtils.defaultIfBlank("", "0"));
	}

	@Test
	public void testPadding() {
		assertEquals("001", StringUtils.leftPad("1", 3, "0"));
		assertEquals("10000", StringUtils.rightPad("1", 5, "0"));
	}
}
