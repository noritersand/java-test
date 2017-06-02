package laboratory.jdk.java.sql;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatementTest {
	private static final Logger log = LoggerFactory.getLogger(StatementTest.class);
	
	@Test
	public void testWhile() {
		int n = 0;
		while (n != 2) { // condition이 true면 반복함
			++n;
			log.debug(String.valueOf(n));
		}
	}
}
