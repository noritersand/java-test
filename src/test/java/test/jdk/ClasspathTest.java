package test.jdk;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.java.io.FileTest;

public class ClasspathTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ClasspathTest.class);
	
	@Test
	public void testGetClasspaths() {
		URL[] url = ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();
		log.debug(Arrays.toString(url));
	}
	
	@Test
	public void testAccessFileAtClasspath() throws URISyntaxException, IOException {
		ClassLoader loader = FileTest.class.getClassLoader();
		URL url = loader.getResource("file/exist-test.txt");
		File file = Paths.get(url.toURI()).toFile();
		Assert.assertTrue(file.exists());
	}
}
