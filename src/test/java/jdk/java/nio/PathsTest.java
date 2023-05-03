package jdk.java.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>{@link Paths} 테스트 슈트
 *
 * @author fixalot
 * @since 2023-03-17
 */
@Slf4j
public class PathsTest {

    @Test
    void initialize() throws IOException {
        Path path = Paths.get("src/test/resources/path-test/amiexist.txt");
        Path path1 = Paths.get(URI.create("file://C:/project/workspace"));
        Path path2 = Paths.get("C:\\project\\workspace");
        Path path3 = Paths.get("localhost/upload");
        Path path4 = Paths.get("/localhost/upload");

        assertEquals("src\\test\\resources\\path-test\\amiexist.txt", path.toString());
        assertEquals("\\\\C\\project\\workspace", path1.toString());
        assertEquals("C:\\project\\workspace", path2.toString());
        assertEquals("localhost\\upload", path3.toString());
        assertEquals("localhost\\upload", path3.toString());
        assertEquals("\\localhost\\upload", path4.toString());
    }

    @Test
    void shouldError() {
        // 없는 경로라도 단순 문자열이면 문제가 없지만
        Paths.get("c:", "\\ppp", "\\aaa");

        // file 프로토콜이 붙으면 파일이 없을때 에러
        assertThrows(InvalidPathException.class, () -> {
            Paths.get("file://c:\\ppp");
        });
    }

    @Test
    void getRoot() {
        log.debug("relative paths:");
        log.debug(Paths.get("/").toString()); // just "\"
        log.debug(Paths.get("").toString()); // ""
        log.debug(Paths.get("/").toFile().toString()); // just "\"
        log.debug(Paths.get("").toFile().toString()); // ""

        log.debug("absolute paths:");
        log.debug(Paths.get("/").toAbsolutePath().toString()); // just "\"
        log.debug(Paths.get("").toAbsolutePath().toString()); // ""
        log.debug(Paths.get("/").toAbsolutePath().toFile().toString()); // just "\"
        log.debug(Paths.get("").toAbsolutePath().toFile().toString()); // ""
    }
}
