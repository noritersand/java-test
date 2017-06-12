package laboratory.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastingTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CastingTest.class);
	
	@Test
	public void castFromNull() {
		Object imnotnotnull = null;
		String string = (String) imnotnotnull;
		Assert.assertNull(string);
	}
	
	@Test
	public void upCasting() {
		
	}
	
	@Test
	public void downCasting() {
		
	}
}
