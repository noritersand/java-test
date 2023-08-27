package builtin.statement.expressions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

/**
 * 람다 표현식 테스트
 * <p>
 * - https://d2.naver.com/helloworld/4911107
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class LambdaTest {

    @Test
    void basicUsage() {
        Map<String, Object> store = new HashMap<>();
        store.put("foo", "bar");
        
        // 이 람다 표현식은
        Predicate<Map<String, Object>> predicated = m ->
            "bar".equals((String) m.get("foo"));

        // 아래의 추상 메서드 구현 코드를 짧게 줄인 것이다.
        Predicate<Map<String, Object>> predicated2 = new Predicate<Map<String, Object>>() {
            @Override
            public boolean test(Map<String, Object> m) {
                return "bar".equals((String) m.get("foo"));
            }
        };
        
        assertThat(predicated.test(store)).isTrue();
        assertThat(predicated2.test(store)).isTrue();
    }

    @Test
    void testLimit() {
        // 하나일 땐 별 문제가 없으나
        ImplementMe impl = (a, b) -> a.equals(b);

        // 아래 코멘트 풀면 컴파일 에러
        // 추상 메서드가 둘 이상이면 람다식을 쓸 수 없다.
        // (Multiple non-overriding abstract methods found in interface builtin.expressions.LambdaTest.ImplementMe2)
//        ImplementMe2 impl2 = (a, b) -> {};
        
        // non-overriding 부분을 테스트해봤으나...
//        ImplementMe3 impl3 = () -> {};
    }

    interface ImplementMe {
        boolean method1(String a, String b);
    }

    interface ImplementMe2 {
        boolean method1(String a, String b);
//        void method2(String a);
    }
    
    interface ImplementMe3 extends ImplementMe2 {
        @Override
        boolean method1(String a, String b);
        
//        void method3();
    }
}


