package jdk.heritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunMe {
    private static final Logger logger = LoggerFactory.getLogger(RunMe.class);

    public static void main(String[] args) {
        System.out.println("begin");
        X obj = new Z();
        obj.f();
        obj.g();
        System.out.println("\ndone");

        Object obj2 = new Wahappen();
        System.out.println(obj2.toString());
    }
}

class X {
    public void f() {
        System.out.print("1");
    }

    public static void g() {
        System.out.print("2");
    }
}

class Y extends X {
    @Override
    public void f() {
        System.out.print("3");
    }
}

class Z extends Y {
    public static void g() {
        System.out.print("4");
    }
}

class Wahappen {

    @Override
    public String toString() {
        return "Wa happening here";
    }
}
