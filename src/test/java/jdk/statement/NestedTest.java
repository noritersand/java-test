package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class NestedTest {

    @Test
    void test() {
        NonStaticOuter outer = new NonStaticOuter();
        assertEquals("invoking outerMethod", outer.executeOuterMethod());

        NonStaticOuter.Inner inner = new NonStaticOuter().getInner();
        assertEquals("invoking innerMethod", inner.executeInnerMethod());
    }

    @Test
    void test2() {
        new Outer().outerMethod();
    }
}

class NonStaticOuter {
    private final String text = "outer";

    public Inner getInner() {
        return new Inner();
    }

    public class Inner {
        private final String text = "inner";

        public String executeInnerMethod() {
            return "invoking " + text + "Method";
        }
    }

    public String executeOuterMethod() {
        return "invoking " + text + "Method";
    }
}

class Outer {
    private static final String a = "static outer member";
    private final String b = "non-static outer member";

    private class Inner {
        //		private static String c = "static inner member"; // non-static 내부 클래스에는 스태틱 멤버를 선언할 수 없음
        private final String d = "non-static inner member";

        private void innerMethod() {
            System.out.println(Outer.a);

            Outer out = new Outer();
            System.out.println(out.b);
            System.out.println(this.d);
        }
    }

    public void outerMethod() {
        System.out.println(a);
        System.out.println(this.b);

        Inner inner = new Inner();
        System.out.println(inner.d);
        inner.innerMethod();
    }
}
