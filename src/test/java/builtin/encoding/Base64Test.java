package builtin.encoding;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BASE64 encoding/decoding tests.
 */
@Slf4j
public class Base64Test {

    @Test
    void testEncode() {
        Base64.Encoder encoder = Base64.getEncoder();
        String str = "Hello World!";
        byte[] encoded = encoder.encode(str.getBytes(StandardCharsets.UTF_8));
        assertArrayEquals(new byte[]{83, 71, 86, 115, 98, 71, 56, 103, 86, 50, 57, 121, 98, 71, 81, 104}, encoded);
        assertEquals("SGVsbG8gV29ybGQh", new String(encoded, StandardCharsets.UTF_8));
    }

    @Test
    void testDecode() {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode("SGVsbG8gV29ybGQh");
        assertEquals("Hello World!", new String(decoded, StandardCharsets.UTF_8));
    }

    @Test
    void shouldBeEquals() {
        Base64.Encoder encoder = Base64.getEncoder();
        assertEquals("YXxi", new String(encoder.encode("a|b".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
        assertEquals("YTpi", new String(encoder.encode("a:b".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
        assertEquals("e2F9OntifQ==", new String(encoder.encode("{a}:{b}".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
    }
}
