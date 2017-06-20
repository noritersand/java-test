package laboratory.jdk.java.nio;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PathTest.class);

	/*
	 * src/main 혹은 src/test는 로컬에서만 사용해야하는 경로다.
	 * 이 경로들은 메이븐 개발 환경에서만 존재하는 폴더 구조이고 war나 jar로 빌드되면 존재하지 않는 경로이기 때문.
	 * 따라서 경로빌드 후 생성될 경로를 절대 경로로 프로퍼티에 관리하는 편이 좋다.
	 */
	
	@Test
	public void relativize() {
		Path dev = new File("c:\\dev\\git").toPath();
		Path sso = new File("c:\\sso").toPath();
		Assert.assertEquals("..\\..\\sso", dev.relativize(sso).toString()); // dev에서 sso로 가려면 'cd ..\..\sso'
		Assert.assertEquals("..\\dev\\git", sso.relativize(dev).toString()); // sso에서 dev로 가려면 'cd ..\dev\git'
		Assert.assertEquals("", dev.relativize(dev).toString()); // dev에서 dev로 가려면 'cd .'
	}
	
	@Test
	public void relativize2() {
		Path source = new File("webapp\\upload\\temp\\").toPath();
		Path target = new File("webapp\\upload\\temp\\201612\\28201838255.png").toPath();
		Assert.assertEquals("201612\\28201838255.png", source.relativize(target).toString()); // dev에서 sso로 가려면 'cd ..\..\sso'
	}
	
	@Test
	public void resolve() throws IOException {
		Path dev = new File("c:\\dev\\git").toPath();
		Path someFile = dev.resolve("someFile");
		Assert.assertEquals("c:\\dev\\git\\someFile", someFile.toString());
	}

	@Test
	public void newInstance() throws IOException {
		Path path = Paths.get("src/test/resources/file/exist-test.txt");
		Path path1 = Paths.get(URI.create("file://C:/project/workspace"));
		Path path2 = Paths.get("C:\\project\\workspace");
		Path path3 = Paths.get("localhost/upload");
		Path path4 = Paths.get("/localhost/upload");
		
		Assert.assertEquals("src\\test\\resources\\file\\exist-test.txt", path.toString());
		Assert.assertEquals("\\\\C\\project\\workspace", path1.toString());
		Assert.assertEquals("C:\\project\\workspace", path2.toString());
		Assert.assertEquals("localhost\\upload", path3.toString());
		Assert.assertEquals("\\localhost\\upload", path4.toString());
	}
	
	@Test
	public void shouldError() {
		// 없는 경로라도 단순 문자열이면 문제가 없지만
		Paths.get("c:", "\\ppp", "\\aaa");
		try {
			// file 프로토콜이 붙으면 파일이 없을때 에러난다.
			Paths.get("file://c:\\ppp");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidPathException);
		}
	}
	
	@Test
	public void getRoot() {
		logger.debug("relative paths:");
		logger.debug(Paths.get("/").toString()); // just "\"
		logger.debug(Paths.get("").toString()); // ""
		logger.debug(Paths.get("/").toFile().toString()); // just "\"
		logger.debug(Paths.get("").toFile().toString()); // ""
		
		logger.debug("absolute paths:");
		logger.debug(Paths.get("/").toAbsolutePath().toString()); // just "\"
		logger.debug(Paths.get("").toAbsolutePath().toString()); // ""
		logger.debug(Paths.get("/").toAbsolutePath().toFile().toString()); // just "\"
		logger.debug(Paths.get("").toAbsolutePath().toFile().toString()); // ""
	}
}
