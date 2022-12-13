package jdk.java.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class FilesTest {
    private static final Logger logger = LoggerFactory.getLogger(FilesTest.class);

    @Test
    public void testProbeContentType() throws IOException {
        assertEquals("text/plain", Files.probeContentType(Paths.get("src/test/resources/files-test/plaintext.txt")));
    }

    @Test
    public void testIsReadable() {
        assertTrue(Files.isReadable(Paths.get("c:")));
        assertFalse(Files.isReadable(Paths.get("m:")));
    }

    @Test
    public void testDelete() throws IOException {
        Path dir = Paths.get("src/test/resources/file/delete-me");
        Files.createDirectories(dir);
        logger.debug(dir.toFile().getAbsolutePath());
        Files.delete(dir);
        assertFalse(Files.exists(dir));

        dir = Paths.get("src/test/resources");
        try {
            Files.delete(dir); // 폴더 하위에 파일이 있으면 예외 발생
        } catch (Exception e) {
            assertTrue(e instanceof DirectoryNotEmptyException);
        }
    }

    @Test
    public void testDeleteIfExists() throws IOException {
        Path dir = Paths.get("src/test/resources/file/delete-me");
        Files.createDirectories(dir);
        assertTrue(Files.deleteIfExists(dir)); // 파일이 있으면 true
        assertFalse(Files.deleteIfExists(dir)); // 파일이 없으면 false. NoSuchFileException은 내부에서 catch 됨

        dir = Paths.get("src/test/resources");
        try {
            Files.deleteIfExists(dir); // 폴더 하위에 파일이 있으면 예외 발생
        } catch (Exception e) {
            assertTrue(e instanceof DirectoryNotEmptyException);
        }
    }
}
