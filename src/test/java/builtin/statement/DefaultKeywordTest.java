package builtin.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
class DefaultKeywordTest {
    @Test
    void test1() {
        C c = new C();
        assertEquals("aaa", c.a());
        assertEquals("hi", c.b());
    }
}

interface I {
    String a();

    default String b() {
        return "hi";
    }
}

class C implements I {

    @Override
    public String a() {
        return "aaa";
    }
}
