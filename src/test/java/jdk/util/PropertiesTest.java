package jdk.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesTest {
	@Test
	public void testPropByInputStream() throws IOException {
	    FileInputStream fis = new FileInputStream("src\\test\\resources\\properties\\test.properties");
	    Properties prop = new Properties();
	    prop.load(fis);
	    fis.close();
		System.out.println(prop);
		
		Assert.assertEquals("{a.b.c=123, web.root=http://daum.net}", prop.toString());
	}
	
	@Test
	public void testPropByReader() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src\\test\\resources\\properties\\test.properties"));
		Properties prop = new Properties();
		prop.load(reader);
		reader.close();
		System.out.println(prop);
		
		Assert.assertEquals("{a.b.c=123, web.root=http://daum.net}", prop.toString());
	}
	
	@Test
	public void testPropFromXml() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\properties\\test-uri.xml");
		Properties prop = new Properties();
		prop.loadFromXML(fis);
		fis.close();
		
		Enumeration<Object> keys = prop.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			System.out.println("key: " + key + ", value: " + prop.getProperty(key));
		}
		
		// key: some.korean, value: 한글
		// key: image.root, value: http://tistory.com
		
		Assert.assertEquals("{some.korean=한글, image.root=http://tistory.com}", prop.toString());
	}
}
