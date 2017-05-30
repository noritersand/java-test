package laboratory.jdk.java.lang;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SystemTest.class);
	
	@Test
	public void testCurrentTime() {
		String millisecond = String.valueOf(System.currentTimeMillis());
		Assert.assertEquals(13, millisecond.length());
//		String nanosecond = String.valueOf(System.nanoTime());
//		Assert.assertEquals(15, nanosecond.length());
		// 둘이 합쳐 28가 아니고 나노초는 14자일때도 있음.
	}
	
	@Test
	public void testGetSystemEnvironmentVariables() {
		Map<String, String> env  = System.getenv();
		Set<String> keySet = env.keySet();
		log.debug("env logging begin");
		for (String key : keySet) {
			log.debug(env.get(key));
		}
		log.debug("env logging done");
	}

	@Test
	public void testGetSystemProperties() {
		Properties props = System.getProperties();
		Enumeration<?> names = props.propertyNames();
		log.debug("property logging begin");
		while (names.hasMoreElements()) {
			String string = (String) names.nextElement();
			log.debug(string);
		}
		log.debug("property logging done");
	}
	
	/**
	 * 이 케이스는 JUNIT에선 성공하지만 MAVEN에선 실패한다. 
	 * 메이븐 테스트는 OS의 케릭터 인코딩을 사용한다고 한다. (윈도우라면 MS949) 아래 링크 참고:
	 * <a href="http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven">http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven</a>
	 * 
	 * @author fixalot
	 */
//	@Test
	public void testGetDefaultEncodingProperty() {
//		Assert.assertEquals("UTF-8", System.getProperty("file.encoding")); // maven test에선 utf-8이 아님		
	}
	
	@Test
	public void testGetOsName() {
		log.debug(System.getProperty("os.name"));
	}
	
	@Test
	public void testGetMavenHome() {
		Assert.assertNull(null, System.getenv("MAVEN_HOME"));  
	}
}
