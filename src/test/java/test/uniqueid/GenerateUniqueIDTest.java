package test.uniqueid;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateUniqueIDTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(GenerateUniqueIDTest.class);
	
	@Test
	public void testGenerateWithSystem() {
		long mills = System.currentTimeMillis();
		long nanos = System.nanoTime();
		log.debug(String.valueOf(mills));
		log.debug(String.valueOf(nanos));		
	}
	
	@Test
	public void testGenereateRandomUUID() {
		log.debug(UUID.randomUUID().toString());
	}
}
