package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

/**
 * Java 10의 신기능 타입 추론(type inference) var 기능 테스트
 */
@Slf4j
public class VarTest {

    /**
     * 컴파일러는 var 변수 우측변에 초기화되는 값으로 타입을 추론한다.
     */
    @Test
    public void basicUsage() {
        var str = "abc";
        assertThat(str).isInstanceOf(String.class);

        var num = 123;
        assertThat(num).isInstanceOf(Integer.class);

        var flt = 123.1F;
        assertThat(flt).isInstanceOf(Float.class);

        var dbl = 123.1; // 타입을 명시하지 않으면 Double로 간주함
        assertThat(dbl).isInstanceOf(Double.class);
    }

    /**
     * 잘못된 사용방법
     */
    @Test
    public void wrongWay() {
        // 타입 추론은 컴파일 타임에 이뤄지기 때문에 null로 초기화할 수 없다. (타입 추론이 안되니까)
//        var a = null; // COMPILE ERROR: Cannot infer type: variable initializer is 'null'

        // 마찬가지로 초기화 없이 선언만 하는 것도 불가능
//        var b; // COMPILE ERROR: Cannot infer type: 'var' on variable without initializer

        // 람다 표현식도 타입 추론 불가능
//        var predicated = m -> "bar".equals((String) m.get("foo")); // COMPILE ERROR: Cannot infer type: lambda expression requires an explicit target type

        // 타입을 생략한 배열 리터럴도 안된다.
//        var arr = { "a", "b" }; // COMPILE ERROR: Array initializer is not allowed here

    }

    // 필드에서도 사용 불가
//    var someField = false; // Cannot resolve symbol 'var'

    // static도 마찬가지
//    static var someField2 = true; // Cannot resolve symbol 'var'
}
