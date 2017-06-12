package laboratory.thirdparty.uid;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateUniqueValueTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(GenerateUniqueValueTest.class);
	
	@Test
	public void getUniqueValue() {
		log.debug("Long max value: " + String.valueOf(Long.MAX_VALUE).length());
		
		long mills = System.currentTimeMillis();
		long nanos = System.nanoTime();

		String millsPlusNanos = String.valueOf(mills) + String.valueOf(nanos);
		if (millsPlusNanos.length() > 19) {
			millsPlusNanos = millsPlusNanos.substring(0, 19);
		}
		Assert.assertEquals(19, millsPlusNanos.length());
		log.debug(String.valueOf(new Long(millsPlusNanos)));
	}
	
	@Test
	public void getUniqueUUID() {
		log.debug(UUID.randomUUID().toString());
		log.debug(String.valueOf(UUID.fromString("cb9bc318-3ba1-4614-81b8-daed4efe6c62")));
//		UUID.nameUUIDFromBytes(1) // 이건 어떻게 쓰는거람
	}
}
