package testbed.forblog;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 깃허브-지킬 블로그 프로젝트의 _drafts와 _posts 폴더 아래에 대문자로 된 파일이 있는지 검사.
 *
 * @author fixalot
 * @since 2018-12-09
 */
@Slf4j
public enum NotLowercaseFilenameFinder {
    ;

    public static void main(String[] args) {
        String[] targetLocations = {
                "C:/dev/git/noritersand.github.io/_drafts",
                "C:/dev/git/noritersand.github.io/_posts"
        };

        try {
            for (String ele : targetLocations) {
                NotLowercaseFilenameFinder.find(ele);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    private static void find(String path) throws IOException {
        log.debug("찾을 위치: {} ", path);

        Path targetPath = new File(path).toPath();
        Stream<Path> targetList = Files.list(targetPath);

        class Counter {
            public int count;
        }
        Counter counter = new Counter();
        targetList.forEach((Path ele) -> {
            String fileName = ele.getFileName().toString();
            String lowerCaseFileName = fileName.toLowerCase();

            if (!fileName.equals(lowerCaseFileName)) {
                log.debug(fileName);
                ++counter.count;
            }
        });
        targetList.close();

        if (0 >= counter.count) {
            log.debug("결과: 발견된 대문자 음슴");
        }
    }
}
