package noritersand;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class JodaTimeTest {
	@Test
	public void testNew() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		String dt = DateTime.now().toString(formatter);
		System.out.println(dt);
	}
}
