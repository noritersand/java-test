package laboratory.util.string;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneNumberSplitterTest {
	private static final Logger logger = LoggerFactory.getLogger(PhoneNumberSplitterTest.class);

	@Test
	public void testSplit() {
		Assert.assertArrayEquals(new String[] { "010", "1234", "1234" }, PhoneNumberSplitter.split("01012341234").getSeparated());
		Assert.assertArrayEquals(new String[] { "02", "1234", "1234" }, PhoneNumberSplitter.split("0212341234").getSeparated());
		Assert.assertArrayEquals(new String[] { "02", "123", "1234" }, PhoneNumberSplitter.split("021231234").getSeparated());
		Assert.assertArrayEquals(new String[] { "0506", "123", "1234" }, PhoneNumberSplitter.split("05061231234").getSeparated());
		Assert.assertArrayEquals(new String[] { "041", "123", "1234" }, PhoneNumberSplitter.split("0411231234").getSeparated());
		Assert.assertArrayEquals(new String[] { "041", "1234", "1234" }, PhoneNumberSplitter.split("04112341234").getSeparated());
		Assert.assertArrayEquals(new String[] { "070", "1234", "1234" }, PhoneNumberSplitter.split("07012341234").getSeparated());
		
		Assert.assertEquals("010-1234-1234", PhoneNumberSplitter.split("01012341234").getJoined());
		Assert.assertEquals("02-1234-1234", PhoneNumberSplitter.split("0212341234").getJoined());
		Assert.assertEquals("02-123-1234", PhoneNumberSplitter.split("021231234").getJoined());
		Assert.assertEquals("0506-123-1234", PhoneNumberSplitter.split("05061231234").getJoined());
		Assert.assertEquals("041-123-1234", PhoneNumberSplitter.split("0411231234").getJoined());
		Assert.assertEquals("041-1234-1234", PhoneNumberSplitter.split("04112341234").getJoined());
		Assert.assertEquals("070-1234-1234", PhoneNumberSplitter.split("07012341234").getJoined());
	}
	
	@Test
	public void shouldBeException() {
		try {
			PhoneNumberSplitter.split("01234567");
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void testToString() {
		logger.debug(PhoneNumberSplitter.split("07012341234").toString());
	}
}
