package thirdparty.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class LogbackTest {
	private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

	public static void main(String[] args) {
		logger.debug("running java application");
	}

	@Test
	public void loggingWithJunit() {
		logger.debug("unit testing");
		logger.debug("unit", "testing"); // "testing"은 안찍힘
	}

	@Test
	public void loggingWithFormat() {
		logger.debug("{} {} {} {}", "이건가", "저건가", "나는 누구", "여긴 어디");
		logger.debug("{} {}", new Object[] { "hello there!", "if you ask, im waldo" });
//		logger.debug("im {}", null); // 이렇게는 안됨
		logger.debug("im {}", "null");
	}
	
//	@Test
	public void loggingError() {
		try {
			throw new IllegalAccessError("1234");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
