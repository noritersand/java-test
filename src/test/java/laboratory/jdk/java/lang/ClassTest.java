package laboratory.jdk.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ClassTest.class);

	@Test
	public void getClassName() {
		Assert.assertEquals("laboratory.jdk.java.lang.ClassTest", ClassTest.class.getName());
	}

	@Test
	public void getPackageName() {
		Assert.assertEquals("laboratory.jdk.java.lang", ClassTest.class.getPackage().getName());
	}
}
