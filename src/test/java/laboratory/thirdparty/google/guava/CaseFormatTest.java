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
		// lower camel -> upper underscore
		Assert.assertEquals("MY_NAME", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "myName"));

		// upper underscore -> lower camel
		Assert.assertEquals("setBulkReservationNotPossible",
				CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "SET_BULK_RESERVATION_NOT_POSSIBLE"));

		// lower hyphen -> lower camel
		Assert.assertEquals("setBulkReservationNotPossible",
				CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "set-bulk-reservation-not-possible"));
	}
}
