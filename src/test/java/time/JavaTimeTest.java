package time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaTimeTest {
	private static final Logger log = LoggerFactory.getLogger(JavaTimeTest.class);

	@Test
	public void test() {
		log.debug("test: " + String.valueOf(Instant.now()));
		log.debug("test: " + String.valueOf(LocalDate.now()));
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
	
	@Test
	public void testDateSplit() {
		LocalDate start = new GregorianCalendar(2016, 2, 5).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = new GregorianCalendar(2016, 2, 11).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		long periodDays = ChronoUnit.DAYS.between(start, end);
		List<LocalDate> list = new LinkedList<>();
		for (int i = 0; i < periodDays; i++) {
			list.add(start.plusDays(i));
		}
		Assert.assertEquals("[2016-03-05, 2016-03-06, 2016-03-07, 2016-03-08, 2016-03-09, 2016-03-10]", list.toString());
	}
	
	@Test
	public void testFromJavaTuilDate() {
		Calendar input = new GregorianCalendar(2016, 2, 5);
		Date date = input.getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Assert.assertEquals("2016-03-05", localDate.toString());
	}
	
	@Test
	public void testToJavaUtilDate() {
		// case#1
		log.debug("testToJavaUtilDate: " + Date.from(Instant.now()).toString());;
		
		// case#2
		log.debug("testToJavaUtilDate: " + Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString());
		
		// case#3
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		log.debug("testToJavaUtilDate: " + out.toString());
	}
}
