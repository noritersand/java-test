package laboratory.test.spring;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/prop/context-test.xml")
public class SpELTest {
	@Value("#{language.primary}")
	private String primary;

	@Value("#{language['secondary']}")
	private String secondary;

	@Value("#{language}")
	private Object language;

	@Value("#{interfaceProp}")
	private Properties interfaceProp;
	
	@Value("#{interfaceProp['interface.base.url']}")
	private Properties interfaceBaseUrl;
	
	// 아래는 에러
//	@Value("#{interfaceProp.interface.base.url}")
//	private Properties interfaceBaseUrl2;

	@Test
	public void testSpEl() {
		Assert.assertEquals("korean", primary);
		Assert.assertNotEquals("korean", secondary);
	}

	@Test
	public void testProp() {
		Assert.assertNotNull(language);
		Assert.assertEquals(Properties.class, language.getClass());
		Assert.assertEquals("korean", ((Properties) language).getProperty("primary"));
	}
	
	@Test
	public void testPropImport() {
		Assert.assertNotNull(interfaceProp);
		Assert.assertEquals("{http=//127.0.0.1:9080/execute}", interfaceBaseUrl.toString());
	}
}
