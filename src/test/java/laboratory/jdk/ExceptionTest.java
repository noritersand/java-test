package laboratory.jdk;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

	@Test
	public void logging() {
		try {
			throw new RuntimeException("for test");			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
