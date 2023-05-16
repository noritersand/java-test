package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TryWithResourcesTest {

    @Test
    void test() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            br = null; // Cannot assign a value to final variable 'br'
            // do something
        } catch (IOException e) {
            // ...
        }
    }
}
