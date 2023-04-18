package jdk.java.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link java.util.UUID} 테스트 슈트
 */
@Slf4j
public class UUIDTest {

    @Test
    public void testRandomUUID() {
        UUID uuid = UUID.randomUUID();
        log.debug("uuid: {}", uuid);
        // 8-4-4-4-12 자릿수 조합의 랜덤 숫자(16진수)로 이루어진 UUID
        // e.g.
        // f3a678d7-67fd-4756-beb8-2fb27f1611df
        // 0f34e015-1120-4a96-9797-66efc347ce49
        assertEquals(36, uuid.toString().length());
        String[] split = uuid.toString().split("-");
        assertEquals(5, split.length);
        assertEquals(8, split[0].length());
        assertEquals(4, split[1].length());
        assertEquals(4, split[2].length());
        assertEquals(4, split[3].length());
        assertEquals(12, split[4].length());
    }

    /**
     * <p>mostSignificantBits는 UUID의 상위 64비트를 나타낸다.</p>
     * <p>반대 개념으로 하위 64비트를 나타내는 leastSignificantBits가 있음.</p>
     */
    @Test
    public void testMostSignificantBits() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        log.debug("mostSignificantBits: {}", mostSignificantBits);
        // 18~19 자리의 숫자(음수부호 제외)를 반환 받음
        // e.g.
        // 7905325634625294181
        // 1346974026921296866
        // 8788065664125059845
        // -3021201585844367800
        // 419997301322891643
        int characterSize = String.valueOf(Math.abs(mostSignificantBits)).length();
        assertTrue(18 <= characterSize && characterSize <= 19);
    }
}
