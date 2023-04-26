package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 캐스팅 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class CastingTest {

    @Test
    public void castNull() {
        Object imnotnotnull = null;
        String string = (String) imnotnotnull;
        assertNull(string);

        Integer integer = (Integer) imnotnotnull;
        assertNull(integer);
    }

    @Test
    public void upCasting() {
        Child c = new Child();
        Parent p = c;
        assertEquals("Child", p.getName());
//		p.play(); // undefined method
    }

    @Test
    public void downCasting() {
        // 요로케는 안됨.
        assertThrows(ClassCastException.class, () -> {
            Parent p = new Parent();
            @SuppressWarnings("unused")
            Child c = (Child) p;
        });

        // 요로케는 됨.
        Parent p = new Child();
        Child c = (Child) p;

        assertEquals("Child", c.getName());
        c.play();
    }

    private class Parent {
        public String getName() {
            return "Parent";
        }
    }

    private class Child extends Parent {
        public void play() {
            log.debug("uoo-hoo!");
        }

        @Override
        public String getName() {
            return "Child";
        }
    }
}
