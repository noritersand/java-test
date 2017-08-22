package laboratory.jdk.java.nio;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
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
public class FilesTest {
	private static final Logger logger = LoggerFactory.getLogger(FilesTest.class);
	
	@Test
	public void probeContentType() throws IOException {
		Path path = Paths.get("src/test/resources/mediatype/plaintext.txt");
		Assert.assertEquals("text/plain", Files.probeContentType(path));
		path = Paths.get("src/test/resources/mediatype/excel.xlsx");
		Assert.assertEquals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", Files.probeContentType(path));
	}
	
	@Test
	public void isReadable() {
		Assert.assertTrue(Files.isReadable(Paths.get("c:")));
		Assert.assertFalse(Files.isReadable(Paths.get("m:")));
	}
	
	@Test
	public void delete() throws IOException {
		Path dir = Paths.get("src/test/resources/file/delete-me");
		Files.createDirectories(dir);
		logger.debug(dir.toFile().getAbsolutePath());
		Files.delete(dir);
		Assert.assertFalse(Files.exists(dir));

		dir = Paths.get("src/test/resources");
		try {
			Files.delete(dir); // 폴더 하위에 파일이 있으면 예외 발생 	
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DirectoryNotEmptyException);
		}
	}
	
	@Test
	public void deleteIfExists() throws IOException {
		Path dir = Paths.get("src/test/resources/file/delete-me");
		Files.createDirectories(dir);
		Assert.assertTrue(Files.deleteIfExists(dir)); // 파일이 있으면 true
		Assert.assertFalse(Files.deleteIfExists(dir)); // 파일이 없으면 false. NoSuchFileException은 내부에서 catch 됨
		
		dir = Paths.get("src/test/resources");
		try {
			Files.deleteIfExists(dir); // 폴더 하위에 파일이 있으면 예외 발생
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DirectoryNotEmptyException);
		}
	}
}
