package jdk.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class DefaultKeywordTest {
    @Test
    public void test1() {
        C c = new C();
        assertEquals("aaa", c.a());
        assertEquals("hi", c.b());
    }
}

interface I {
    public String a();

    default public String b() {
        return "hi";
    }
}

class C implements I {

    @Override
    public String a() {
        return "aaa";
    }
}
