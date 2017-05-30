package laboratory.jdk.java.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(FileTest.class);

	@Test
	public void testNewInstance() {
		File file = new File("/qweasdqweasd"); // 이클립스를 실행한 드라이브의 루트의 qweasdqweasd 폴더
		Assert.assertFalse(file.exists());
	}

	@Test
	public void testNewInstanceFromPath() {
		Path path = Paths.get("src/test/resources/file/exist-test.txt");
		File file = path.toFile();
		Assert.assertTrue(file.exists());
	}
}
