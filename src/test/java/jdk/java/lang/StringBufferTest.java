package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * StringBuffer 테스트
 *
 * @author fixal
 * @since 2018-09-28
 */
@Slf4j
public class StringBufferTest {

    /**
     * 한글을 3바이트로 처리하는 UTF-8의 바이트배열을 4바이트씩 잘라서 String으로 만들면 한글이 망가지는데,
     * StringBuffer를 써도 마찬가지인지 확인하는 테스트 케이스
     *
     * @author fixal
     */
    @Test
    void concatBytes() {
        final String origin = "가나다라";
        byte[] bytes = origin.getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(new byte[]{-22, -80, -128, -21, -126, -104, -21, -117, -92, -21, -99, -68}, bytes);

        assertEquals("가", new String(bytes, 0, 3, StandardCharsets.UTF_8));
        assertEquals("나", new String(bytes, 3, 3, StandardCharsets.UTF_8));
        assertEquals("다", new String(bytes, 6, 3, StandardCharsets.UTF_8));
        assertEquals("라", new String(bytes, 9, 3, StandardCharsets.UTF_8));

        String a, b, c;
        assertEquals("가�", a = new String(bytes, 0, 4, StandardCharsets.UTF_8));
        assertEquals("���", b = new String(bytes, 4, 4, StandardCharsets.UTF_8));
        assertEquals("�라", c = new String(bytes, 8, 4, StandardCharsets.UTF_8));

        assertEquals("가�����라", a + b + c);
    }

    @Test
    void testDelete() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("abc").append("def");
        assertEquals(6, buffer.length());

        buffer.delete(0, buffer.length());
        assertEquals(0, buffer.length());
    }

    /**
     * StringBuilder도 쓰는 방법은 비슷함
     */
    @Test
    void testStringBuilder() {
        StringBuilder builder = new StringBuilder();
        builder.append("abc").append("def");
        assertEquals(6, builder.length());

        builder.delete(0, builder.length());
        assertEquals(0, builder.length());
    }
}
