package builtin.statement;

import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class OverridingTest {
    @Test
    void override() {

    }
}

class Parent {
    public void m01() {

    }

    public static void m02() {

    }
}

class Child extends Parent {
    @Override
    public void m01() {

    }

    public static void m02() {

    }
}
