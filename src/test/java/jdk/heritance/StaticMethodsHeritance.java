package jdk.heritance;

public class StaticMethodsHeritance {
    public static void main(String[] args) {
        Parent instance = new Child();
        instance.print(); // Child 출력됨
        instance.staticPrint(); // Parent 출력됨
    }
}

class Parent {
    public void print() {
        System.out.println("Parent");
    }

    public static void staticPrint() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    public void print() {
        System.out.println("Child");
    }

    public static void staticPrint() {
        System.out.println("Child");
    }
}
