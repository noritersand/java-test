package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@link System} 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class SystemTest {

    @Test
    void getCurrentTime() {
        String millisecond = String.valueOf(System.currentTimeMillis());
        assertEquals(13, millisecond.length());
//		String nanosecond = String.valueOf(System.nanoTime());
//		assertEquals(15, nanosecond.length());
        // 둘이 합쳐 28이 아니고 나노초는 14자일때도 있음.
    }

    /**
     * 모든 환경 변수 조회.
     */
    @Test
    void getSystemEnvironmentVariables() {
        Map<String, String> env = System.getenv();
        Set<String> keySet = env.keySet();
        for (String envname : keySet) {
            log.debug("{}={}", envname, env.get(envname));
        }
    }

    /**
     * 모든 시스템 프로퍼티 조회.
     */
    @Test
    void getSystemProperties() {
        Properties props = System.getProperties();
        Enumeration<?> names = props.propertyNames();
        while (names.hasMoreElements()) {
            String propname = (String) names.nextElement();
            log.debug("{}={}", propname, props.getProperty(propname));
        }
    }

    @Test
    void getDefaultEncodingProperty() {
        log.debug("file.encoding={}", System.getProperty("file.encoding"));
        log.debug("default character set={}", Charset.defaultCharset());
        assertEquals(Charset.defaultCharset().toString(), System.getProperty("file.encoding").toUpperCase());
    }

    @Test
    void shouldBeNull() {
        assertNull(System.getProperty("i'm not here"));
    }

//	@Test
//	public void testLoadLibrary() {
//		System.load("c:/some-file-name");
//		System.loadLibrary("some-library-name");
//	}
}
