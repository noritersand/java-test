package thirdparty.google.guava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class CaseFormatTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CaseFormatTest.class);

	@Test
	public void testFormat() {
		// lower camel -> upper underscore
		assertEquals("MY_NAME", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "myName"));

		// upper underscore -> lower camel
		assertEquals("setBulkReservationNotPossible",
				CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "SET_BULK_RESERVATION_NOT_POSSIBLE"));

		// lower hyphen -> lower camel
		assertEquals("setBulkReservationNotPossible",
				CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "set-bulk-reservation-not-possible"));
	}
}
