package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Java unlimited method arguments test case
 *
 * @author fixalot
 * @since 2018-04-03
 */
@Slf4j
public class MethodArgumentsTest {

    public static void testMe(int... args) {
        for (int ele : args) {
            log.debug("{}", ele);
        }
    }

    @Test
    public void test() {
        MethodArgumentsTest.testMe(1, 2, 3, 4, 5);
    }
}
