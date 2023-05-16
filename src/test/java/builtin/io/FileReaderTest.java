package builtin.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class FileReaderTest {

    @Test
    void compareEachLine() throws IOException {
        File file = new File("src/test/resources/file-reader-test/amiexist.txt");
        File file2 = new File("src/test/resources/file-reader-test/writeonme.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2, StandardCharsets.UTF_8));

        String data = null;
        String data2 = null;
        boolean hasAnyEqualLine = false;
        while (null != (data = reader.readLine())) { // 읽을게 없으면 null 리턴
            data2 = reader2.readLine();
            if (null == data2) {
                break;
            }
            if (data.equals(data2)) {
                hasAnyEqualLine = true;
                log.debug(data);
            }
        }
        if (!hasAnyEqualLine) {
            log.debug("모든 라인이 일치하지 않네요");
        }

        reader.close();
        reader2.close();
    }
}
