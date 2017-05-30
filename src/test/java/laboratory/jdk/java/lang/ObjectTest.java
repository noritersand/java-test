package laboratory.jdk.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ObjectTest.class);
	
	@Test
	public void testHashCode() {
		Object obj1 = new Object();
		Object obj2 = new Object();
		log.debug(String.valueOf(obj1.hashCode()));
		log.debug(String.valueOf(obj2.hashCode()));
		Assert.assertNotEquals(obj1.hashCode(), obj2.hashCode());
	}
}
