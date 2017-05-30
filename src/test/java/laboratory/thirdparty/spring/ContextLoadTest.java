package laboratory.thirdparty.spring;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextLoadTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ContextLoadTest.class);

	@Test
	public void testContextLoadWithClassPath() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/prop/context-test.xml");
		Properties props = context.getBean("language", Properties.class);
		Assert.assertEquals("korean", props.getProperty("primary"));
		context.close();
	}

	@Test
	public void testContextLoadWithFileSystem() {
//		ConfigurableApplicationContext context 
//				= new FileSystemXmlApplicationContext("/prop/test-context.xml"); // FileNotFoundException
		ConfigurableApplicationContext context = new FileSystemXmlApplicationContext("classpath:/prop/context-test.xml");
		Properties props = context.getBean("language", Properties.class);
		Assert.assertEquals("korean", props.getProperty("primary"));
		context.close();
	}
}
