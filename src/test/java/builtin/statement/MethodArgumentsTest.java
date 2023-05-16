package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Java unlimited method arguments test case
 *
 * @author fixalot
 * @since 2018-04-03
 */
@Slf4j
public class MethodArgumentsTest {

    /**
     * 일반적인 사용 방법
     */
    @Test
    void testArguments() {
        log.debug("{}", "First call");
        assertEquals(5, testMe1(1, 2, 3, 4, 5));

        // 전달인자를 생략하면 빈 배열이 전달됨
        log.debug("{}", "Second call");
        assertEquals(0, testMe1());
    }

    public int testMe1(int... args) {
        return args.length;
    }

    /**
     * method arguments가 있는 메서드를 오버로딩하면 어떻게 되는지 테스트
     */
    @Test
    void testOverloading() {
        assertEquals(999, testMe2()); // 오버로딩한 메서드를 호출함
        assertEquals(3, testMe2(2, 3, 4));
    }

    public int testMe2() {
        return 999;
    }

    public int testMe2(int... args) {
        return args.length;
    }
}
