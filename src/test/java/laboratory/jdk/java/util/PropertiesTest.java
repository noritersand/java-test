package laboratory.jdk.java.util;

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

public class PropertiesTest {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesTest.class);

	@Test
	public void getPropByInputStream() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\prop\\access-test.properties");
		Properties prop = new Properties();
		prop.load(fis);
		fis.close();
		System.out.println(prop);

		Assert.assertEquals("{a.b.c=123, web.root=http://daum.net}", prop.toString());
	}

	@Test
	public void getPropByReader() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src\\test\\resources\\prop\\access-test.properties"));
		Properties prop = new Properties();
		prop.load(reader);
		reader.close();
		logger.debug(String.valueOf(prop));

		Assert.assertEquals("{a.b.c=123, web.root=http://daum.net}", prop.toString());
	}

	@Test
	public void getPropFromXml() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\prop\\uri-test.xml");
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

		Assert.assertEquals("{some.korean=한글, image.root=http://tistory.com}", prop.toString());
	}
}
