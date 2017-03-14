package test.java.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(FileTest.class);

	@Test
	public void testNewInstance() {
		File file = new File("/test"); // 이클립스를 실행한 드라이브의 루트
		log.debug(String.valueOf(file.exists()));
	}
	
	@Test
	public void testNewInstanceFromPath() {
		Path path = Paths.get("/test");
		File file = path.toFile();
		log.debug(String.valueOf(file.exists()));
	}
}
