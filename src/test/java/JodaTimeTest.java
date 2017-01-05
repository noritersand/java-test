import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

public class JodaTimeTest {
	private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");
	
	@Test
	public void testNew() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		String dt = DateTime.now().toString(formatter);
		System.out.println(dt);
	}
	
	@Test
	public void testToFormatString() {
		DateTime dt = new DateTime();
		System.out.println("dt: " + dt);
		
		DateTime newDt = dt.withYear(2020).withMonthOfYear(2).withDayOfMonth(29).withHourOfDay(23)
				.withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(10);
		
		Assert.assertEquals("2020-02-29T23:59:59.010+09:00", newDt.toString());
		Assert.assertEquals("2020-02-29 23:59:59:010", newDt.toString(formatter));
	}
	
	@Test
	public void testEqual() {
		Assert.assertEquals(new DateTime(), DateTime.now());
		Assert.assertEquals(new DateTime(2020, 2, 29, 23, 59, 59, 10), 
				new DateTime().withYear(2020).withMonthOfYear(2).withDayOfMonth(29).withHourOfDay(23)
						.withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(10));
	}
	
	@Test
	public void testFromFormatString() {
		Assert.assertEquals(new DateTime(2020, 2, 29, 23, 59, 59, 10), 
				formatter.parseDateTime("2020-02-29 23:59:59:010"));
	}
	
	@Test
	public void testAddDate() {
		DateTime dt = new DateTime(2020, 2, 29, 23, 59, 59, 10);
		DateTime newInstance = dt.plusDays(2);
		Assert.assertEquals("2020-03-02 23:59:59:010", newInstance.toString(formatter));
	}
}
