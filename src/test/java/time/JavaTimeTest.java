package time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaTimeTest {
	private static final Logger log = LoggerFactory.getLogger(JavaTimeTest.class);

	@Test
	public void test() {
		log.debug("NOW: " + String.valueOf(Instant.now()));
		log.debug("NOW: " + String.valueOf(LocalDate.now()));
	}
	
	@Test
	public void testLocalDate() {
		Assert.assertEquals("2017-12-31", LocalDate.of(2017, Month.DECEMBER, 31).toString());
		Assert.assertEquals("2017-04-10T23:49", LocalDateTime.of(2017, Month.APRIL, 10, 23, 49).toString());
	}
	
	@Test
	public void testOffsetDateTime() {
		Assert.assertEquals("2017-12-31T23:59:59.999999999Z",
				OffsetDateTime.of(LocalDate.of(2017, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC).toString());
		Assert.assertEquals("2017-01-14T10:20:30Z", OffsetDateTime.of(2017, 1, 14, 10, 20, 30, 0, ZoneOffset.UTC).toString());
	}
	
	@Test
	public void testOffsetTime() {
		Assert.assertEquals("22:58+18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MAX).toString());
		Assert.assertEquals("22:58-18:00", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.MIN).toString());
		Assert.assertEquals("22:58Z", OffsetTime.of(LocalTime.of(22, 58), ZoneOffset.UTC).toString());
	}
}
