package laboratory.forblog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotLowercaseFilenameFinder {
	private static final Logger logger = LoggerFactory.getLogger(NotLowercaseFilenameFinder.class);

	public static void main(String[] args) throws Exception {
		String[] targetLocations = { 
				"C:\\\\dev\\\\git\\\\noritersand.github.io\\\\_drafts", 
				"C:\\\\dev\\\\git\\\\noritersand.github.io\\\\_posts" 
				};

		for (String ele : targetLocations) {
			find(ele);
		}
	}

	private static void find(String path) throws IOException {
		Path targetPath = new File(path).toPath();
		Stream<Path> targetList = Files.list(targetPath);

		targetList.forEach((ele) -> {
			final String fileName = ele.getFileName().toString();

			if (!fileName.equals(fileName.toLowerCase())) {
				logger.debug(fileName);
			}
		});

		targetList.close();
	}
}
