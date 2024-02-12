package builtin.statement;

import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
class OverridingTest {

    @Test
    void override() {

    }
    private static class Parent {
        public void m01() {

        }

        public static void m02() {

        }
    }
    private static class Child extends Parent {
        @Override
        public void m01() {

        }

        public static void m02() {

        }
    }

}
