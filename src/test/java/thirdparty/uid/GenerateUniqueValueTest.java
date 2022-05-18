package thirdparty.uid;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class GenerateUniqueValueTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GenerateUniqueValueTest.class);

	@Test
	public void getUniqueValue() {
		logger.debug("Long max value: " + String.valueOf(Long.MAX_VALUE).length());

		long mills = System.currentTimeMillis();
		long nanos = System.nanoTime();

		String millsPlusNanos = String.valueOf(mills) + String.valueOf(nanos);
		if (millsPlusNanos.length() > 19) {
			millsPlusNanos = millsPlusNanos.substring(0, 19);
		}
		assertEquals(19, millsPlusNanos.length());
		logger.debug("{}", Long.valueOf(millsPlusNanos));
	}

	@Test
	public void getUniqueUuid() {
		logger.debug(UUID.randomUUID().toString());
		logger.debug("{}", UUID.fromString("cb9bc318-3ba1-4614-81b8-daed4efe6c62"));
//		UUID.nameUUIDFromBytes(1) // 이건 어떻게 쓰는거람
	}
}
