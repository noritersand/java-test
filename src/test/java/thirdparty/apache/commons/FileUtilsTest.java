package thirdparty.apache.commons;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class FileUtilsTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);

    /**
     * 파일 읽고 쓰기
     *
     * @throws IOException
     * @author fixalot
     */
    @Test
    public void writeAndRead() throws IOException {
        String text = "some\ntext";
        File file = Paths.get("src/test/resources/file-utils-test/writeonme.txt").toFile();

        // write
        FileUtils.writeStringToFile(file, text, Charset.forName("UTF-8"));

        // read
        String textFromFile = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        assertEquals("some\ntext", textFromFile);
    }

    /**
     * URL에서 응답한 결과를 파일로 쓰기.
     * 요청의 헤더 정보는 별다른건 없고 GET, HTTP/1.1 정도.
     * 설명을 못찾아서 추측하건데, 인코딩은 아마 응답 헤더에 따라 결정되는것 같다.
     *
     * @throws IOException
     * @author fixalot
     */
    @Test
    public void testCopyURLToFile() throws IOException {
        final URL source = new URL("http://google.com");
        final File destination = Paths.get("src/test/resources/file/file-from-url.txt").toFile();
        FileUtils.copyURLToFile(source, destination);
        assertTrue(destination.exists());
        destination.delete();
    }
}
