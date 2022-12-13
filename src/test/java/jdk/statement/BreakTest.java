package jdk.statement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * break문 테스트
 *
 * @author noritersand
 * @since 2020-04-03
 */
public class BreakTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(BreakTest.class);

    private static final boolean T = true;
    private static final boolean F = false;

    @Test
    public void withLabeledIfStatement() {
        int n = 10;
        hello:
        if (n > 0) {
            assertTrue(T);
            if (n == 10) {
                assertTrue(T);
                break hello; // hello if를 탈출
            }
            assertTrue(F); // 실행되지 않음
        }
    }

    @Test
    public void withLabeledBlock() {
        char c = 'A';
        yoohoo:
        {
            if (c == 'A') {
                assertTrue(T);
                break yoohoo; // yoohoo 블록을 탈출
            }
            assertTrue(F); // 실행되지 않음
        }
    }
}
