package jdk.heritance;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class HeritanceTest {

    @Test
    void test() {
        X obj = new Z();
        assertEquals("3", obj.f());
        assertEquals("2", X.g());

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
