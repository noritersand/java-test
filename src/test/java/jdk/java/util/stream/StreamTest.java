package jdk.java.util.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 테스트 유닛: 스트림과 람다
 * 
 * required jdk8 or higher
 * 
 * @since 2018-07-16
 * @author fixalot
 */
public class StreamTest {
	private static final Logger logger = LoggerFactory.getLogger(StreamTest.class);

	@Test
	public void test() throws IOException {
		Path path = new File("/").toPath();
		Stream<Path> list = Files.list(path);
		list.forEach(System.out::println);
		list.close();

		Path path2 = new File("").toPath();
		Stream<Path> list2 = Files.list(path2);
		list2.forEach((k) -> {
			logger.debug(k.toString());
		});
		list2.close();
	}
}
