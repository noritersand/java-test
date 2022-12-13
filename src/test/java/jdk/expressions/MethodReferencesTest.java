package jdk.expressions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Java 8 신규 기능 메서드 참조
 *
 * @author noritersand
 * @since 2020-12-26
 */
@Slf4j
public class MethodReferencesTest {

    @Test
    public void testMethodReference2() {
        final String txt = "Hello";
        // 아래 넷은 모두 결과가 같다.

        // #1 내부 클래스를 이용하는 방식
        Bar callback = new Bar() {
            @Override
            public void say(String str) {
                log.debug(str);
            }
        };
        Foo.call(txt, callback);

        // #2 익명 클래스를 이용하는 방식
        Foo.call(txt, new Bar() {
            @Override
            public void say(String str) {
                log.debug(str);
            }
        });

        // #3 1, 2를 줄여서 쓸 수 있게 나온게 람다 표현식
//		Foo.call(txt, s -> log.debug(s)); // 아래와 같다.
        Foo.call(txt, (s) -> {
            log.debug(s);
        });

        // #4 3을 줄여서 나온게 메서드 참조
        Foo.call(txt, log::debug);
        Foo.call(txt, System.out::println);
    }

    /**
     * 메서드 참조를 사용할 수 없는 경우
     *
     * @author masca
     */
    @Test
    public void testMethodReference3() {
        final String txt = "Hi";
        Foo.call(txt, (str) -> {
            log.debug(str);
            log.info(str);
        });
        // 위처럼 두 줄 이상의 실행부는 메서드 참조로 대체할 수 없음.
        // 메서드 참조는, 람다 표현식의 바디에서 단 하나의 호출 표현식만 작성할 경우에만 적용할 수 있다.
//	    Foo.call(txt, log::debug;log::info); // Syntax error on token ";", , expected
    }

    enum Foo {
        ;

        static void call(String txt, Bar callback) {
            callback.say(txt);
        }
    }

    interface Bar {
        void say(String str);
    }
}
