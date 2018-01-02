package jdk18.java.lang;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
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

	@Test
	public void getSystemEnvironmentVariables() {
		Map<String, String> env = System.getenv();
		Set<String> keySet = env.keySet();
		logger.debug("env logging begin");
		for (String key : keySet) {
			logger.debug(env.get(key));
		}
		logger.debug("env logging done");
	}

	@Test
	public void shouldBeNull() {
		Assert.assertNull(System.getProperty("i'm not here"));
	}
	
	@Test
	public void getSystemProperties() {
		Properties props = System.getProperties();
		Enumeration<?> names = props.propertyNames();
		logger.debug("property logging begin");
		while (names.hasMoreElements()) {
			String string = (String) names.nextElement();
			logger.debug(string);
		}
		logger.debug("property logging done");
	}

	/**
	 * 이 케이스는 JUNIT에선 성공하지만 MAVEN에선 실패한다. 메이븐 테스트는 OS의 케릭터 인코딩을 사용한다고 한다. (윈도우라면 MS949) 아래 링크 참고: <a href=
	 * "http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven">http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven</a>
	 * 
	 * @author fixalot
	 */
//	@Test
	public void getDefaultEncodingProperty() {
//		Assert.assertEquals("UTF-8", System.getProperty("file.encoding")); // maven test에선 utf-8이 아님		
	}

	@Test
	public void getEmptyProperty() {
		Assert.assertNull(System.getProperty("this.is.must.be.null"));
	}

	@Test
	public void getOsName() {
		logger.debug(System.getProperty("os.name"));
	}

	@Test
	public void getMavenHome() {
		Assert.assertNull(null, System.getenv("MAVEN_HOME"));
	}
}
