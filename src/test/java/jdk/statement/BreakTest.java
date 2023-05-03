package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * break문 테스트
 *
 * @author noritersand
 * @since 2020-04-03
 */
@Slf4j
public class BreakTest {

    private static final boolean T = true;
    private static final boolean F = false;

    @Test
    void withLabeledIfStatement() {
        int n = 10;
        hello:
        if (0 < n) {
            assertTrue(T);
            if (10 == n) {
                assertTrue(T);
                break hello; // hello if를 탈출
            }
            assertTrue(F); // 실행되지 않음
        }
    }

    @Test
    void withLabeledBlock() {
        char c = 'A';
        yoohoo:
        {
            if ('A' == c) {
                assertTrue(T);
                break yoohoo; // yoohoo 블록을 탈출
            }
            assertTrue(F); // 실행되지 않음
        }
    }
}
