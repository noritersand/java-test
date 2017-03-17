package test.apache.commons;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lang3Test {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(Lang3Test.class);

	@Test
	public void testPadding() {
		Assert.assertEquals("001", StringUtils.leftPad("1", 3, "0"));
		Assert.assertEquals("10000", StringUtils.rightPad("1", 5, "0"));
	}
}
