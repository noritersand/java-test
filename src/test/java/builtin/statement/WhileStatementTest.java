package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * while문 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class WhileStatementTest {

    @Test
    void testWhile() {
        int n = 0;
        while (2 != n) { // condition이 true면 반복함
            ++n;
            log.debug("{}", n);
        }
        assertEquals(2, n);
    }
}
