package time;

import java.time.Instant;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaTimeTest {
	private static final Logger log = LoggerFactory.getLogger(JavaTimeTest.class);

	@Test
	public void test() {
		// Period.ofMonths(12);
		log.debug(String.valueOf(Instant.now()));
	}
}
