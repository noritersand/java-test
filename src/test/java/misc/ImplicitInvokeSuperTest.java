package misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ImplicitInvokeSuperTest {

    @Test
    void test() {
        Child child = new Child();
        assertThat(child).isNotNull();
    }

    private static class Parent {
        String nation;

        Parent() {
            this("대한민국");
            System.out.println("Parent() call");
        }

        Parent(String nation) {
            this.nation = nation;
            System.out.println("Parent(String nation) call");
        }
    }

    private static class Child extends Parent {
        String name;

        Child() {
            this("홍길동");
            System.out.println("Child() call");
        }

        Child(String name) {
            this.name = name;
            System.out.println("Child(String name) call");
        }
    }

}
