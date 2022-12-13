package jdk.java.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * java.util.Scanner 사용방법 테스트
 *
 * @author fixalot
 * @since 2017-09-25
 */
@Slf4j
public class ScannerTest {

    private static final String FULL_TEXT = "abcdefg hijklmn	opqrstu|vwxyz\n12345,67890\n가나다라마바사^아자차카타파하";

    @Test
    public void initialize() {
        Scanner scanner = new Scanner(FULL_TEXT);
        assertNotNull(scanner);
        scanner.close();
    }

    /**
     * Scanner.next()는 스트림을 읽어서 한 단어씩 반환한다.
     * 여기서 한 단어의 기준은 delimiter를 따로 지정하지 않으면 '공백/탭/줄바꿈'이다.
     *
     * @author fixalot
     */
    @Test
    public void testNext() {
        Scanner scanner = new Scanner(FULL_TEXT);
        assertEquals("\\p{javaWhitespace}+", scanner.delimiter().toString());
        while (scanner.hasNext()) {
            String text = scanner.next();
            log.debug(text);
        }
        scanner.close();
    }

    /**
     * delimiter를 '^'로 지정한 경우. '공백/탭/줄바꿈'을 구분자로 인식하지 않음.
     *
     * @author fixalot
     */
    @Test
    public void testNextWithCustomDelimiter() {
        Scanner scanner = new Scanner(FULL_TEXT);
        scanner.useDelimiter("\\^");
        while (scanner.hasNext()) {
            String text = scanner.next();
            log.debug(text);
        }
        scanner.close();
    }

    /**
     * Scanner.nextLine()은 스트림을 읽어서 한 줄씩 반환한다.
     *
     * @author fixalot
     */
    @Test
    public void testNextLine() {
        Scanner scanner = new Scanner(FULL_TEXT);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            log.debug(text);
        }
        scanner.close();
    }
}
