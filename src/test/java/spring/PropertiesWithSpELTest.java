package spring;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/properties/test-context.xml")
//@ContextConfiguration(locations = "classpath:propertyTest/test-property-context.xml")
public class PropertiesWithSpELTest {
	@Value("#{language.primary}")
	private String primary;

	@Value("#{language['secondary']}")
	private String secondary;

	@Value("#{language}")
	private Object test;

	@Test
	public void testSpEl() {
		Assert.assertEquals("korean", primary);
		Assert.assertNotEquals("korean", secondary);
	}

	@Test
	public void testProp() {
		Assert.assertNotNull(test);
		Assert.assertEquals(Properties.class, test.getClass());
		Assert.assertEquals("korean", ((Properties) test).getProperty("primary"));
	}
}
