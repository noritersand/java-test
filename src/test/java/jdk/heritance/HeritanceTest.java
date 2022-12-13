package jdk.heritance;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeritanceTest {
    private static final Logger logger = LoggerFactory.getLogger(HeritanceTest.class);

    @Test
    public void test() {
        X obj = new Z();
        assertEquals("3", obj.f());
        assertEquals("2", obj.g());

        Object obj2 = new Wahappen();
        assertEquals("Wa happening here", obj2.toString());
    }
}

class X {
    public String f() {
        return "1";
    }

    public static String g() {
        return "2";
    }
}

class Y extends X {
    @Override
    public String f() {
        return "3";
    }
}

class Z extends Y {
    public static String g() {
        return "4";
    }
}

class Wahappen {

    @Override
    public String toString() {
        return "Wa happening here";
    }
}
