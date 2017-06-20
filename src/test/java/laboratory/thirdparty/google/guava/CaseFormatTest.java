package laboratory.thirdparty.google.guava;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

public class CaseFormatTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CaseFormatTest.class);
	
	@Test
	public void format() {
		Assert.assertEquals("MY_NAME", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "myName"));
		Assert.assertEquals("setBulkReservationNotPossible", 
				CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "set-bulk-reservation-not-possible"));
	}
}
