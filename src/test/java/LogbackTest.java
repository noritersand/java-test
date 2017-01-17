

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	private static final Logger log = LoggerFactory.getLogger(LogbackTest.class);
	
	public static void main(String[] args) {
		log.debug("running java application");
	}
	
	@Test
	public void testLoggingWhileJunit() {
		log.debug("unit testing");
	}
}
