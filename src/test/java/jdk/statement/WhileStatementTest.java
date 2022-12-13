package jdk.statement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * while문 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
public class WhileStatementTest {
    private static final Logger logger = LoggerFactory.getLogger(WhileStatementTest.class);

    @Test
    public void testWhile() {
        int n = 0;
        while (n != 2) { // condition이 true면 반복함
            ++n;
            logger.debug("{}", n);
        }
        assertEquals(2, n);
    }
}
