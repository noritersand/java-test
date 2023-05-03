package jdk.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 'pass by value' test unit
 *
 * @author fixalot
 * @since 2019-06-18
 */
@Slf4j
public class PassByValueTest {

    @Test
    void test() {
        String a = "a";
        final String b = "b";
        reassign(a);
        reassign(b);
        assertEquals("a", a);
        assertEquals("b", b); // 다른 메서드에서 재할당해봐야 안바뀜.

        // String만 그런가?

        List<String> list = new LinkedList<>();
        int hashCode = list.hashCode();
        reassign(list);
        assertEquals(hashCode, list.hashCode()); // 높. 딴 것도 마찬가지

        // 자바는 언제나 'pass by value'이기 때문에 요렇다.
        // 그래서 '파라미터를 재할당하지 말 것'이라는 코딩 규칙이 의미가 있는지 싶다.

        mutate(list);
        assertTrue(0 < list.size());
        assertEquals("Grr", list.get(0)); // 이건 좀 얘기가 다름
    }

    private void reassign(String arg) {
        arg = "c";
    }

    private void reassign(List<String> arg) {
        arg = new LinkedList<>();
    }

    private void mutate(List<String> arg) {
        arg.add("Grr");
    }
}
