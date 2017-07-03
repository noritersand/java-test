package laboratory.thirdparty.apache.commons;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);

	@Test
	public void writeAndRead() throws IOException {
		String text = "some\ntext";
		File file = Paths.get("src/test/resources/file/write-test.txt").toFile();

		// write
		FileUtils.writeStringToFile(file, text, Charset.forName("UTF-8"));

		// read
		String textFromFile = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
		Assert.assertEquals("some\ntext", textFromFile);
	}
}
