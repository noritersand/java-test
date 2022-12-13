package jdk.java.io;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class FileTest {
    private static final Logger logger = LoggerFactory.getLogger(FileTest.class);

    /*
     * src/main 혹은 src/test는 이클립스에서 직접 VM을 실행할 때에만 사용해야하는 경로다. 이 경로들은 메이븐 개발 환경에서만 존재하는 폴더 구조이고 war나 jar로 빌드되면 존재하지 않는 경로이기 때문.
     * 따라서 테스트 케이스가 아니라면, 파일 경로는 src/main가 아닌 절대 경로이면서 프로퍼티(properties 혹은 xml)로 관리해야 한다.
     */

    @Test
    public void initialize() {
        File file = new File("/im-g-root"); // 이클립스를 실행한 드라이브의 루트의 qweasdqweasd 폴더
        assertFalse(file.exists());
    }

    @Test
    public void initializeWithPath() {
        Path path = Paths.get("src/test/resources/file-test/amiexist.txt");
        logger.debug(path.toString());
        File file = path.toFile();
        assertEquals(path.toString(), file.toString());
        logger.debug(file.getAbsolutePath());
        assertEquals(file.getAbsolutePath(), file.getAbsoluteFile().toString());
        assertTrue(file.exists());
    }

    @Test
    public void testCreateTempFile() throws IOException {
        File file = File.createTempFile("head-", ".tmp");
        logger.debug(file.getPath());
    }
}
