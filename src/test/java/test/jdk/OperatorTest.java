package test.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(OperatorTest.class);
	
	@Test
	public void testTernary1() {
		String first = "1";
		String second = "2";
		String third = "3";
		String fourth = "";

		String result = (!fourth.isEmpty()) ? fourth : (!third.isEmpty()) ? third : (!second.isEmpty()) ? second : first;
		Assert.assertEquals("3", result);
	}
}
