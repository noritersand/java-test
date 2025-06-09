package builtin.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO class description
 */
@Slf4j
public class MessageDigestTest {

    @Test
    void testDigest() throws NoSuchAlgorithmException {
        final String input = "rjqskrlsqlalfqlalfqlalfqjsgh123";
        String hexString = getHexString(input);

        assertThat(hexString).isEqualTo("70f88deb4079af6d516e4f9eccd71dc586f66b420cf57cd3c936b10721a0d204d3c9caff0e3c04b6cf0bf409653113657ee970320401c6fa99091ca7368b2b88");
    }

    private String getHexString(String plainText) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-512");
        byte[] hashBytes = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

}
