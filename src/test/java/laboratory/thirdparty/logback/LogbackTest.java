package laboratory.thirdparty.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	private static final Logger log = LoggerFactory.getLogger(LogbackTest.class);

	public static void main(String[] args) {
		log.debug("running java application");
	}

	@Test
	public void loggingWhileJunit() {
		log.debug("unit testing");
		log.debug("unit", "testing"); // "testing"은 안찍힘
	}

	@Test
	public void loggingByFormat() {
		log.debug("{} {} {} {}", "이건가", "저건가", "나는 누구", "여긴 어디");
		log.debug("{} {}", new Object[] { "hello there!", "if you ask, im waldo" });
//		log.debug("im {}", null); // 이렇게는 안됨
		log.debug("im {}", "null");
	}
}
