package laboratory.thirdparty.google.guava;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Booleans;

public class BooleansTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BooleansTest.class);
	
	@Test
	public void test() {
		Assert.assertEquals(5, Booleans.asList(false, true, false, true, true).size());
		Assert.assertEquals("true, false", Booleans.join(", ", true, false));
	}
}
