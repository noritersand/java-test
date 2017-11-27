package laboratory.jdk18.statement;

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
public class CastingTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CastingTest.class);

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
