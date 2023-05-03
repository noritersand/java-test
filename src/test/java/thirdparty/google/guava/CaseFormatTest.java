package thirdparty.google.guava;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class CaseFormatTest {

    @Test
    void testFormat() {
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
