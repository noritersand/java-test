package laboratory;

import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculateElapsedTimeTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CalculateElapsedTimeTest.class);

	@Test
	public void calculate() {
		long start = new GregorianCalendar(2016, 2, 5).getTimeInMillis();
		long end = new GregorianCalendar(2017, 2, 5).getTimeInMillis();
		long elapsedDays = (end - start) / 1000 / 60 / 60 / 24;
		Assert.assertEquals(365, elapsedDays);
		
		start = new GregorianCalendar(2016, 2, 5, 21, 59, 59).getTimeInMillis();
		end = new GregorianCalendar(2016, 2, 5, 23, 59, 59).getTimeInMillis();
		elapsedDays = (end - start) / 1000;
		Assert.assertTrue(86400 > elapsedDays); // 2시간 전이라 86400초(하루) 보다 작다.
		
		start = new GregorianCalendar(2016, 2, 5).getTimeInMillis();
		end = new GregorianCalendar(2016, 2, 5, 23, 59, 59).getTimeInMillis();
		elapsedDays = (end - start) / 1000;
		Assert.assertTrue(86400 > elapsedDays); // 23시간 59분 59초 전
		
		start = new GregorianCalendar(2016, 2, 4).getTimeInMillis();
		end = new GregorianCalendar(2016, 2, 6).getTimeInMillis();
		elapsedDays = (end - start) / 1000;
		Assert.assertTrue(86400 < elapsedDays); // 이틀 전이라 86400초(하루) 보다 크다.
	}
}
