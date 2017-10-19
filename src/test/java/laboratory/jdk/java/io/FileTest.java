package laboratory.jdk.java.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class FileTest {
	private static final Logger logger = LoggerFactory.getLogger(FileTest.class);

	/*
	 * src/main 혹은 src/test는 로컬에서만 사용해야하는 경로다. 이 경로들은 메이븐 개발 환경에서만 존재하는 폴더 구조이고 war나 jar로 빌드되면 존재하지 않는 경로이기 때문. 따라서 경로빌드 후 생성될 경로를 절대 경로로
	 * 프로퍼티에 관리하는 편이 좋다.
	 */

	@Test
	public void getNewInstance() {
		File file = new File("/qweasdqweasd"); // 이클립스를 실행한 드라이브의 루트의 qweasdqweasd 폴더
		Assert.assertFalse(file.exists());
	}

	@Test
	public void getNewInstanceFromPath() {
		Path path = Paths.get("src/test/resources/file-test/amiexist.txt");
		logger.debug(path.toString());
		File file = path.toFile();
		Assert.assertEquals(path.toString(), file.toString());
		logger.debug(file.getAbsolutePath());
		Assert.assertEquals(file.getAbsolutePath(), file.getAbsoluteFile().toString());
		Assert.assertTrue(file.exists());
	}
}
