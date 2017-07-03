package laboratory.jdk.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderTest {
	private static final Logger logger = LoggerFactory.getLogger(FileReaderTest.class);

	@Test
	public void compareEachLine() throws IOException {
		File file = new File("src/test/resources/file/exist-test.txt");
		File file2 = new File("src/test/resources/file/write-test.txt");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));

		String data = null;
		String data2 = null;
		boolean hasAnyEqualLine = false;
		while ((data = reader.readLine()) != null) { // 읽을게 없으면 null 리턴
			data2 = reader2.readLine();
			if (data2 == null) {
				break;
			}
			if (data.equals(data2)) {
				hasAnyEqualLine = true;
				logger.debug(data);
			}
		}
		if (!hasAnyEqualLine) {
			logger.debug("모든 라인이 일치하지 않네요");
		}

		reader.close();
		reader2.close();
	}
}
