package laboratory.forblog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 깃허브-지킬 블로그 프로젝트의 _drafts와 _posts 폴더 아래에 대문자로 된 파일이 있는지 검사.
 * 
 * @since 2018-12-09
 * @author fixalot
 */
public class NotLowercaseFilenameFinder {
	private static final Logger logger = LoggerFactory.getLogger(NotLowercaseFilenameFinder.class);

	public static void main(String[] args) throws Exception {
		String[] targetLocations = { 
			"C:/dev/git/noritersand.github.io/_drafts", 
			"C:/dev/git/noritersand.github.io/_posts" 
		};

		for (String ele : targetLocations) {
			find(ele);
		}
	}

	private static void find(String path) throws IOException {
		Path targetPath = new File(path).toPath();
		Stream<Path> targetList = Files.list(targetPath);

		targetList.forEach(ele -> {
			final String fileName = ele.getFileName().toString();
			final String lowerCaseFileName = fileName.toLowerCase();

			if (!fileName.equals(lowerCaseFileName)) {
				logger.debug(fileName);
			}
		});
		targetList.close();
	}
}
