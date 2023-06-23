package builtin.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * java.util.Optional 테스트 슈ㅌ
 */
@Slf4j
public class OptionalTest {

    @Test
    void testEmpty() {
        Optional<Object> empty = Optional.empty();
        assertThat(empty).isNotNull();
        assertThat(empty).isEmpty();
        assertThat(empty.isEmpty()).isTrue();
        assertThat(empty.isPresent()).isFalse();
    }

    /**
     * <p>Optional 인스턴스를 생성하는 메서드 of() 테스트</p>
     * <p>null이 주어지면 NPE 발생함</p>
     */
    @Test
    void testOf() {
        Optional<String> op = Optional.of("1234");
        assertThat(op.get()).isEqualTo("1234");
        assertThat(op.toString()).isEqualTo("Optional[1234]");
        assertThat(op).isEqualTo(Optional.of("1234"));

        assertThatThrownBy(() -> {
            Optional<String> op2 = Optional.of(null);
        }).isInstanceOf(NullPointerException.class);
    }

    /**
     * <p>ofNullable()은 of()와 달리 NPE에 안전함</p>
     */
    @Test
    void testOfNullable() {
        Optional<String> op = Optional.ofNullable("1234");
    }

    /**
     * <p>isPresent()는 Optional에 값이 있으면 true를 반환함</p>
     */
    @Test
    void testIsPresent() {
        assertThat(Optional.of("1234").isPresent()).isTrue();
        assertThat(Optional.of("").isPresent()).isTrue(); // 빈 문자열인지는 판단하지 않음
        assertThat(Optional.ofNullable("1234").isPresent()).isTrue();
    }

    /**
     * <p>isEmpty()는 isPresent()와 반대로 Optional에 값이 비어있어야 true를 반환함</p>
     */
    @Test
    void testIsEmpty() {
        assertThat(Optional.ofNullable(null).isEmpty()).isTrue();
        assertThat(Optional.of("1234").isEmpty()).isFalse();
        assertThat(Optional.of("").isEmpty()).isFalse();
    }

    /**
     * <p>Optional의 값이 있으면 실행할 콜백 메서드</p>
     */
    @Test
    void testIfPresent() {
        String str = "qwer";
        Optional<String> op = Optional.of(str);
        op.ifPresent(s -> {
            log.debug("{}", "It is presented.");
        });
    }

    /**
     * <p>값이 비어 있으면(null이면) 실행할 콜백 메서드까지 지정할 수 있음</p>
     */
    @Test
    void testIfPresentOrElse() {
        String str = null;
        Optional<String> so = Optional.ofNullable(str);
        so.ifPresentOrElse(s -> {
            log.debug("{}", "It is presented.");
        }, () -> {
            log.debug("{}", "It is not presented.");
        });
    }

    @Test
    void testFilter() {
        // 이렇게 쓰는 뇨솤들이 있음
        String str2 = "hi";
        Optional<String> op2 = Optional.ofNullable(str2).filter(s -> {
            log.debug("{}", "이 코드는 실행됨");
            return true;
        });
        log.debug("op2: {}", op2);

        // filter() 메서드는 Optional의 값이 없을 때 실행되지 않는다.
        String str = null;
        Optional<String> op = Optional.ofNullable(str).filter(s -> {
            log.debug("{}", "이 코드는 실행되지 않음");
            return true;
        });
        log.debug("op: {}", op);

        // filter()에서 false를 반환하면 Optional은 값이 없는 상태가 된다.
        Optional<String> abcd = Optional.ofNullable("abcd").filter(s -> false);
        assertThat(abcd.isEmpty()).isTrue();
    }

    @Test
    void testMap() {
        String str = "1234";
        Optional<String> op = Optional.ofNullable(str).map(s -> {
            log.debug("{}", "이 코드는 실행됨");
            return "numbers";
        });
        assertThat(op.get()).isEqualTo("numbers");

        // map()도 filter()처럼 Optional의 값이 없을 때 실행되지 않는다.
        String str2 = null;
        Optional<String> op2 = Optional.ofNullable(str2).map(s2 -> {
            log.debug("{}", "이 코드는 실행되지 않음");
            return "numbers";
        });
        assertThat(op2.orElseGet(() -> null)).isNull();

        // filter()에서 false를 반환하면 Optional은 값이 없는 상태가 되기 때문에 map()도 실행되지 않는다.
        Optional<String> op3 = Optional.ofNullable("abcd").filter(s -> false).map(s -> {
            log.debug("{}", "이 코드는 실행되지 않음");
            return "numbers";
        });
        assertThat(op3.isEmpty()).isTrue();

        // null이던 null-string이던 NPE에서 안전함
        String str3 = null;
//        String str3 = "";
        Integer userNo = Optional.ofNullable(str3)
                .filter(v -> !v.isBlank())
                .map(Integer::valueOf).orElse(null);
        assertThat(userNo).isNull();

        // 하지만 문자열로 "null"인 경우는 얘기가 다름
        assertThatThrownBy(() -> {
            String str4 = "null";
            Optional.ofNullable(str4)
                    .filter(v -> !v.isBlank())
                    .map(s -> {
                        // Integer.valueOf()를 하려다 NFE 발생함
                        return Integer.valueOf(s);
                    }).orElse(null);
        }).isInstanceOf(NumberFormatException.class);
    }

    /**
     * or()은 Optional의 값이 null일 때 실행되며 Optional을 반환한다.
     */
    @Test
    void testOr() {
        String str = null;
        Optional<String> op = Optional.ofNullable(str).or(() -> {
            log.debug("{}", "이건 뭘까");
            return "hello".describeConstable();
        });
        assertThat(op).isEqualTo(Optional.of("hello"));
    }

    /**
     * orElse()는 or() 처럼 Optional의 값이 null일 때 실행되지만 Optional이 아닌 값을 반환한다. (이 코드에선 String)
     */
    @Test
    void testOrElse() {
        String str = null;
        String str2 = Optional.ofNullable(str).orElse("yoo-hoo");
        assertThat(str2).isEqualTo("yoo-hoo");
    }

    /**
     * Optional을 이용해 null-safe한 타입변환 예시
     */
    @Test
    void nullSafeConvert() {
        String tisNull = null;

        // 원래는 NPE 때문에 이렇게 해야 하는데
        Integer memberNo = StringUtils.isBlank(tisNull) ? null : Integer.valueOf(tisNull);
        assertThat(memberNo).isNull();

        // Optional을 쓴다면
        Integer memberNo2 = Optional.ofNullable(tisNull)
                .filter(str -> !str.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        // 설명: 
        // 1. filter()를 통해 null-string인지 검사하고
        // 2. map()에서 Integer로 변환한다.
        // 3. 만약 값이 null이면 filter()와 map()을 건너뛰며 바로 orElse()를 실행함
        // 4. 만약 값이 null-string이면 filter()에서 Optional의 값을 비우므로 map()을 건너뛰고 orElse()를 실행한다.

        assertThat(memberNo2).isNull(); // 그래서 결국 null이 할당됨
    }
}
