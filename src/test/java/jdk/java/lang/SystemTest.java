package jdk.java.lang;

import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link System} 테스트 유닛
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class SystemTest {
	private static final Logger logger = LoggerFactory.getLogger(SystemTest.class);

	@Test
	public void getCurrentTime() {
		String millisecond = String.valueOf(System.currentTimeMillis());
		Assert.assertEquals(13, millisecond.length());
//		String nanosecond = String.valueOf(System.nanoTime());
//		Assert.assertEquals(15, nanosecond.length());
		// 둘이 합쳐 28이 아니고 나노초는 14자일때도 있음.
	}

	/**
	 * 모든 환경 변수 조회.
	 */
	@Test
	public void getSystemEnvironmentVariables() {
		Map<String, String> env = System.getenv();
		Set<String> keySet = env.keySet();
		for (String envname : keySet) {
			logger.debug("{}={}", envname, env.get(envname));
		}
	}

	/**
	 * 모든 시스템 프로퍼티 조회.
	 */
	@Test
	public void getSystemProperties() {
		Properties props = System.getProperties();
		Enumeration<?> names = props.propertyNames();
		while (names.hasMoreElements()) {
			final String propname = (String) names.nextElement();
			logger.debug("{}={}", propname, props.getProperty(propname));
		}
	}

	@Test
	public void getDefaultEncodingProperty() {
		logger.debug("file.encoding={}", System.getProperty("file.encoding"));
		logger.debug("default character set={}", Charset.defaultCharset());
		Assert.assertEquals(Charset.defaultCharset().toString(), System.getProperty("file.encoding").toUpperCase());
	}

	@Test
	public void shouldBeNull() {
		Assert.assertNull(System.getProperty("i'm not here"));
	}

//	@Test
//	public void testLoadLibrary() {
//		System.load("c:/some-file-name");
//		System.loadLibrary("some-library-name");
//	}
}
