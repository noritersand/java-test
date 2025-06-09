package thirdparty.apache;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Apache Tika 클래스 테스트
 * <p></p>
 * Tika는 파일의 컨텐츠 타입(MIME 타입, 미디어 타입)을 감지할 때 사용함
 */
@Slf4j
public class TikaTest {

    @Test
    public void testDetect() throws IOException {
        Tika tika = new Tika();
        assertThat(tika.detect(new File("src/test/resources/images/sample1.png"))).isEqualTo("image/png");
        assertThat(tika.detect(new File("src/test/resources/images/sample2.png"))).isEqualTo("image/png");
        assertThat(tika.detect(new File("src/test/resources/images/sample3.png"))).isEqualTo("image/png");
        assertThat(tika.detect(new File("src/test/resources/images/sample4.png"))).isEqualTo("image/png");
        assertThat(tika.detect(new File("src/test/resources/images/sample5.gif"))).isEqualTo("image/gif");
        assertThat(tika.detect(new File("src/test/resources/images/sample6.jpg"))).isEqualTo("image/jpeg");
        assertThat(tika.detect(new File("src/test/resources/images/sample7.txt"))).isEqualTo("text/plain");
    }
}

