package laboratory.thirdparty.spring;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/prop/context-test.xml")
public class SpElTest {
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
	public void validateProperties() {
		Assert.assertEquals("korean", primary);
		Assert.assertNotEquals("korean", secondary);

		Assert.assertNotNull(language);
		Assert.assertEquals(Properties.class, language.getClass());
		Assert.assertEquals("korean", ((Properties) language).getProperty("primary"));

		Assert.assertNotNull(interfaceProp);
		Assert.assertEquals("{http=//127.0.0.1:9080/execute}", interfaceBaseUrl.toString());
	}
	
	/**
	 * 환경 변수가 없으면 @Value의 value값으로 초기화된다.
	 */
	@Value("${not-exist-argument}")
	private String arg;
	
	@Test
	public void notExist() {
		Assert.assertEquals("${not-exist-argument}", arg);
	}

	/**
	 * 맨 끝에 :를 붙여주면 환경변수가 없을때 nullstring으로 초기화 됨. 
	 */
	@Value("${not-exist-argument2:}")
	private String arg2;
	
	@Test
	public void notExist2() {
		Assert.assertEquals("", arg2);
	}	
}
