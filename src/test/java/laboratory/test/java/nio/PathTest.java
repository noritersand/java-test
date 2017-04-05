package laboratory.test.java.nio;
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
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(PathTest.class);
	
	@Test
	public void testRelative() {
		Path dev = new File("c:\\dev\\git").toPath();
		Path sso = new File("c:\\sso").toPath();
		Assert.assertEquals("..\\..\\sso", dev.relativize(sso).toString()); // dev에서 sso로 가려면 'cd ..\..\sso'
		Assert.assertEquals("..\\dev\\git", sso.relativize(dev).toString()); // sso에서 dev로 가려면 'cd ..\dev\git'
		Assert.assertEquals("", dev.relativize(dev).toString()); // dev에서 dev로 가려면 'cd .'
	}
	
	@Test
	public void testRelative2() {
		Path source = new File("webapp\\upload\\temp\\").toPath();
		Path target = new File("webapp\\upload\\temp\\201612\\28201838255.png").toPath();
		Assert.assertEquals("201612\\28201838255.png", source.relativize(target).toString()); // dev에서 sso로 가려면 'cd ..\..\sso'
	}
	
	@Test
	public void testResolve() throws IOException {
		Path dev = new File("c:\\dev\\git").toPath();
		Path someFile = dev.resolve("someFile");
		Assert.assertEquals("c:\\dev\\git\\someFile", someFile.toString());
	}

	@Test
	public void testNewInstnc() throws IOException {
		Path path1 = Paths.get(URI.create("file://C:/project/workspace"));
		Path path2 = Paths.get("C:\\project\\workspace");
		Path path3 = Paths.get("localhost/upload");
		Path path4 = Paths.get("/localhost/upload");
		
		Assert.assertEquals("\\\\C\\project\\workspace", path1.toString());
		Assert.assertEquals("C:\\project\\workspace", path2.toString());
		Assert.assertEquals("localhost\\upload", path3.toString());
		Assert.assertEquals("\\localhost\\upload", path4.toString());
		
//		Path path5 = Paths.get("C:/project/workspace/etbs/pmd/ruleset.xml");
//		File file5 = path5.toFile();
//		Assert.assertTrue(file5.exists());
//		log.debug(FileUtils.readFileToString(file5, "UTF-8"));
	}
	
	@Test
	public void testVoidFile() {
		// 없는 경로라도 단순 문자열이면 문제가 없지만
		Paths.get("c:", "\\ppp", "\\aaa");
		try {
			// file 프로토콜이 붙으면 파일이 없을때 에러난다.
			Paths.get("file://c:\\ppp");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidPathException);
		}
	}
}
