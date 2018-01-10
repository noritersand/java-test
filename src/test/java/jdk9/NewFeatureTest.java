package jdk9;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewFeatureTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(NewFeatureTest.class);
	
	@Test
	public void test() {
		@SuppressWarnings("rawtypes")
		List immutableList = List.of();
		Assert.assertNotNull(immutableList);
	}
}
