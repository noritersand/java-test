package jdk.java.math;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2018-01-23
 * @author fixalot
 */
public class MathTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MathTest.class);

	@Test
	public void test() {
		logger.debug(String.valueOf(Math.random()));
	}
}
