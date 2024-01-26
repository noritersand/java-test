package thirdparty.uid;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class GenerateUniqueValueTest {

    @Test
    void getUniqueValue() {
        log.debug("Long max value: {}", String.valueOf(Long.MAX_VALUE).length());

        long mills = System.currentTimeMillis();
        long nanos = System.nanoTime();

        String millsPlusNanos = String.valueOf(mills) + nanos;
        if (19 < millsPlusNanos.length()) {
            millsPlusNanos = millsPlusNanos.substring(0, 19);
        }
        assertEquals(19, millsPlusNanos.length());
        log.debug("{}", Long.valueOf(millsPlusNanos));
    }

    @Test
    void getUniqueUuid() {
        log.debug(UUID.randomUUID().toString());
        log.debug("{}", UUID.fromString("cb9bc318-3ba1-4614-81b8-daed4efe6c62"));
//		UUID.nameUUIDFromBytes(1) // 이건 어떻게 쓰는거람
    }
}
