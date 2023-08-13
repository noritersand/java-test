package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class NestedTest {

    @Test
    void testNonStaticOuter() {
        NonStaticOuter outer = new NonStaticOuter();
        assertEquals("invoking outerMethod", outer.executeOuterMethod());

        // static이 아닌 public 중첩 클래스는 외부 클래스의 인스턴스를 통해 접근해야 함
        NonStaticOuter.Inner inner = outer.new Inner();
        assertEquals("invoking innerMethod", inner.executeInnerMethod());
    }

    @Test
    void testOuter() {
        Outer outer = new Outer();
        outer.outerMethod();

        // static public 중첩 클래스는 이렇게 접근함
        assertThat(Outer.StaticInner.staticValue).isEqualTo("staticValue");

        // static public 중첩 클래스의 필드는 인스턴스를 먼저 생성해야 한다.
        Outer.StaticInner staticInner = new Outer.StaticInner();
        assertThat(staticInner.nonStaticValue).isEqualTo("nonStaticValue");
    }

    @Test
    void testOuter2() {

    }

}

class NonStaticOuter {
    private final String text = "outer";

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

    public static class StaticInner {
        public String nonStaticValue = "nonStaticValue";
        public static String staticValue = "staticValue";
    }

    public void outerMethod() {
        System.out.println(a);
        System.out.println(this.b);

        Inner inner = new Inner();
        System.out.println(inner.d);

        // private으로 접근을 제어하고 있지만 중첩 클래스의 메서드는 둘러싸는 외부 클래스에서도 접근 가능함
        inner.innerMethod();
    }
}
