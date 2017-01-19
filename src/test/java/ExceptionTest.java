import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ExceptionTest.class);

	@Test
	public void testLog() {
		try {
			throw new RuntimeException("for test");			
		} catch (Exception e) {
//			log.error(e.getMessage(), e);
		}
	}
}
