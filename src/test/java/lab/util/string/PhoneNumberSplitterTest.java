package lab.util.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class PhoneNumberSplitterTest {

    @Test
    public void testSplit() {
        assertArrayEquals(new String[]{"010", "1234", "1234"}, PhoneNumberSplitter.split("01012341234").getSeparated());
        assertArrayEquals(new String[]{"02", "1234", "1234"}, PhoneNumberSplitter.split("0212341234").getSeparated());
        assertArrayEquals(new String[]{"02", "123", "1234"}, PhoneNumberSplitter.split("021231234").getSeparated());
        assertArrayEquals(new String[]{"0506", "123", "1234"}, PhoneNumberSplitter.split("05061231234").getSeparated());
        assertArrayEquals(new String[]{"041", "123", "1234"}, PhoneNumberSplitter.split("0411231234").getSeparated());
        assertArrayEquals(new String[]{"041", "1234", "1234"}, PhoneNumberSplitter.split("04112341234").getSeparated());
        assertArrayEquals(new String[]{"070", "1234", "1234"}, PhoneNumberSplitter.split("07012341234").getSeparated());

        assertEquals("010-1234-1234", PhoneNumberSplitter.split("01012341234").getJoined());
        assertEquals("02-1234-1234", PhoneNumberSplitter.split("0212341234").getJoined());
        assertEquals("02-123-1234", PhoneNumberSplitter.split("021231234").getJoined());
        assertEquals("0506-123-1234", PhoneNumberSplitter.split("05061231234").getJoined());
        assertEquals("041-123-1234", PhoneNumberSplitter.split("0411231234").getJoined());
        assertEquals("041-1234-1234", PhoneNumberSplitter.split("04112341234").getJoined());
        assertEquals("070-1234-1234", PhoneNumberSplitter.split("07012341234").getJoined());
    }

    @Test
    public void shouldBeException() {
        try {
            PhoneNumberSplitter.split("01234567");
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testToString() {
        log.debug(PhoneNumberSplitter.split("07012341234").toString());
    }
}
