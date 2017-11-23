package laboratory.jdk18.java.lang;

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
public class ObjectTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ObjectTest.class);

	@Test
	public void getHashCode() {
		Object obj1 = new Object();
		Object obj2 = new Object();
		logger.debug(String.valueOf(obj1.hashCode()));
		logger.debug(String.valueOf(obj2.hashCode()));
		Assert.assertNotEquals(obj1.hashCode(), obj2.hashCode());
	}
	
	@Test
	public void getString() {
		Object obj1 = new Object();
		Object[] obj2 = new Object[] { null };
		logger.debug(obj1.toString());
		logger.debug(obj2.toString());
		
		Assert.assertTrue(obj1.toString().startsWith("java.lang")); // 일반 객체는 패키지부터 시작
		Assert.assertTrue(obj2.toString().startsWith("[Ljava.lang")); // 배열은 [L 다음에 패키지 시작
	}
}
