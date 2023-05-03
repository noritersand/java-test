package jdk.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 인터페이스 테스트 슈트
 *
 * @author noritersand
 * @since 2021-01-06
 */
@Slf4j
public class InterfaceTest {

    @Test
    void test() {
        String fourfivesix = ImplementMe.fourFiveSix;
        assertEquals(fourfivesix, "456");
        ImplementMe aa = new ImplementMe() {

            @Override
            public String getText2() {

                return null;
            }
        };
        assertEquals("Hi.", aa.sayHello());
    }
}

interface ImplementMe {
    String oneTwoThree = "123";
    String fourFiveSix = "456";

    static String getAng() {
        return "ang?";
    }

    String getText2();

    default String sayHello() {
        return "Hi.";
    }
}
