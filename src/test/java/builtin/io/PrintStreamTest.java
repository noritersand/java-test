package builtin.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * PrintStream(STDOUT) 테스트
 *
 * @author fixalot
 * @since 2018-08-22
 */
@Slf4j
public class PrintStreamTest {

    @Test
    void testCharset() throws UnsupportedEncodingException {
        final String txt = "한글";
        log.debug(txt); // logger는 System.out 설정과 상관없는걸로 추정. logger는 인코딩 설정이 따로 있나?

        System.out.println(txt); // 한글

        PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        ps.println(txt); // 한글

        // 깨짐. 아마 UTF-8로 작성된 문자를 다른 케릭터셋으로 출력하려 해서 깨지는듯?
        ps = new PrintStream(System.out, true, "euc-kr");
        ps.println(txt); // �ѱ�
        System.setOut(ps);
        System.out.println(txt); // �ѱ�

        // 깨짐. 위와 마찬가지
        ps = new PrintStream(System.out, true, StandardCharsets.ISO_8859_1);
        ps.println(txt); // ??
        System.setOut(ps);
        System.out.println(txt); // ??
    }
}
