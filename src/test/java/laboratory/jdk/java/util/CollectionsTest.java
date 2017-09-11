package laboratory.jdk.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CollectionsTest.class);

	@Test
	public void getMinAndMax() {
		List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
		Assert.assertEquals(new Integer(1), Collections.min(list));
		Assert.assertEquals(new Integer(99), Collections.max(list));
	}
}
