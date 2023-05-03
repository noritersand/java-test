package jdk.java.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>{@link Files} 클래스 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class FilesTest {

    @Test
    void testProbeContentType() throws IOException {
        assertEquals("text/plain", Files.probeContentType(Paths.get("src/test/resources/files-test/plaintext.txt")));
    }

    @Test
    void testIsReadable() {
        assertTrue(Files.isReadable(Paths.get("c:")));
        assertFalse(Files.isReadable(Paths.get("m:")));
    }

    @Test
    void testDelete() throws IOException {
        Path dir = Paths.get("src/test/resources/file/delete-me");
        Files.createDirectories(dir);
        log.debug(dir.toFile().getAbsolutePath());
        Files.delete(dir);
        assertFalse(Files.exists(dir));

        dir = Paths.get("src/test/resources/");
        Path finalDir = dir;
        assertThrows(DirectoryNotEmptyException.class, () -> {
            Files.delete(finalDir);
        });
    }

    @Test
    void testDeleteIfExists() throws IOException {
        Path dir = Paths.get("src/test/resources/file/delete-me");
        Files.createDirectories(dir);
        assertTrue(Files.deleteIfExists(dir)); // 파일이 있으면 true
        assertFalse(Files.deleteIfExists(dir)); // 파일이 없으면 false. NoSuchFileException은 내부에서 catch 됨

        dir = Paths.get("src/test/resources");
        Path finalDir = dir;
        assertThrows(DirectoryNotEmptyException.class, () -> {
            Files.deleteIfExists(finalDir);
        });
    }
}
