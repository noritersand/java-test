package jdk.java.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

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
public class PropertiesTest {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesTest.class);

	@Test
	public void getPropByInputStream() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\properties-test\\access-test.properties");
		Properties prop = new Properties();
		prop.load(fis);
		fis.close();
		System.out.println(prop);

		Assert.assertEquals("{web.root=http://daum.net, a.b.c=123}", prop.toString());
	}

	@Test
	public void getPropByReader() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src\\test\\resources\\properties-test\\access-test.properties"));
		Properties prop = new Properties();
		prop.load(reader);
		reader.close();
		logger.debug(String.valueOf(prop));

		Assert.assertEquals("{web.root=http://daum.net, a.b.c=123}", prop.toString());
	}

	@Test
	public void getPropFromXml() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\properties-test\\uri-test.xml");
		Properties prop = new Properties();
		prop.loadFromXML(fis);
		fis.close();

		Enumeration<Object> keys = prop.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			logger.debug("key: " + key + ", value: " + prop.getProperty(key));
		}

		// key: some.korean, value: 한글
		// key: image.root, value: http://tistory.com

		Assert.assertEquals("{image.root=http://tistory.com, some.korean=한글}", prop.toString());
	}
}
