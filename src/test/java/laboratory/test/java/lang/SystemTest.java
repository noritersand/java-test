package laboratory.test.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SystemTest.class);
	
	/**
	 * 이 케이스는 JUNIT에선 성공하지만 MAVEN에선 실패한다. 
	 * 메이븐 테스트는 OS의 케릭터 인코딩을 사용한다고 한다. (윈도우라면 MS949) 아래 링크 참고:
	 * <a href="http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven">http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven</a>
	 * 
	 * @author fixalot
	 */
//	@Test
	public void testSystemProperties() {
		Assert.assertEquals("UTF-8", System.getProperty("file.encoding"));;
	}
	
	@Test
	public void testGetMavenHome() {
		Assert.assertNull(null, System.getenv("MAVEN_HOME"));  
	}
}
