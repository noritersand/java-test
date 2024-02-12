package builtin.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * {@link HashMap} 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class HashMapTest {

    /**
     * 해시맵은 엔트리(내부의 구성 요소)가 같으면 동등하다고 판단함.
     *
     * @author noritersand
     */
    @Test
    void testEquals() {
        HashMap<String, Object> x = new HashMap<>();
        HashMap<String, Object> y = new HashMap<>();
        x.put("a", "b");
        y.put("a", "b");

        // 인스턴스는 다르지만
        assertThat(x).isNotSameAs(y);

        // AbstractMap.hashCode()는 엔트리의 hashCode()를 이어 붙인 값이다. 따라서 같다고 판단한다.
        assertThat(x.hashCode() == y.hashCode()).isTrue();

        // AbstractMap.equals()는 엔트리의 키:값 구성이 같은지를 비교한다. 따라서 둘은 동등하다.
        assertThat(x.equals(y)).isTrue();
    }

    /**
     * 변경 불가능한 맵(unmodifiable map) 객체를 반환하는 Map.of() 메서드 테스트
     */
    @Test
    void testOf() {
        Map<String, Integer> map = Map.of("a", 1, "c", 2);
        assertThat(map.get("a")).isEqualTo(1);
        assertThat(map.get("c")).isEqualTo(2);

        assertThatThrownBy(() -> {
            map.put("b", 5);
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage(null);
    }

    @Test
    void testEntry() {
        HashMap<String, String> map = new HashMap<>();
        map.put("first", "1st");
        map.put("second", "2nd");
        map.put("third", "3rd");

        Set<Map.Entry<String, String>> entry = map.entrySet();
        for (Map.Entry<String, String> ele : entry) {
            String key = ele.getKey();
            String value = ele.getValue();
            log.debug("key: {}, value: {}", key, value);
        }
    }

    @Test
    void shouldBeNull() {
        HashMap<String, String> map = new HashMap<>();
        assertThat(map.get("야")).isNull();
    }

    @Test
    void checkNPE() {
        Map<String, Object> map = new HashMap<>();
        String empty = (String) map.get("empty");
        assertThat(empty).isNull();

        map.put("NOT_NULL", null);
        Object notNull = map.get("NOT_NULL");
        assertThat(notNull).isNull();
    }

    /**
     * 맵도 반복문을 돌려서 꺼낼 수 있다.
     * 왜냐믄 맵의 키셋에 iterator가 있기 때문
     *
     * @author fixalot
     */
    @Test
    void possibleLoopStatement() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1234);
        map.put("b", 5678);
        map.put("c", 90);

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            assertThat(map.get(key)).isNotNull();
        }
    }

    @Test
    void testForEach() {
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.forEach((k, v) -> {
            log.debug("Item : {} Count : {}", k, v);
        });
    }

    @Test
    void copyMapToAnotherMap() {
        Map<String, Integer> original = new HashMap<>();
        original.put("A", 10);
        original.put("B", 20);

        // ## #1 생성자로 복사하기
        HashMap<String, Integer> copy = new HashMap<>(original);
        assertThat(copy).isEqualTo(original).isNotSameAs(original);

        // ⚠️ 생성자로 복사할 때 null을 넣으면 NPE 발생함
        assertThatThrownBy(() ->
            new HashMap<>(null)
        ).isInstanceOf(NullPointerException.class).hasMessage("Cannot invoke \"java.util.Map.size()\" because \"m\" is null");

        // ⚠️ 얕은 복사니까 주의
        HashMap<String, BigDecimal> shallowOrigin = new HashMap<>();
        BigDecimal bigDecimal = new BigDecimal(65536);
        shallowOrigin.put("first", bigDecimal);
        HashMap<String, BigDecimal> shallowCopy = new HashMap<>(shallowOrigin);
        assertThat(shallowCopy).containsEntry("first", bigDecimal);

        // ## #2 Map.copyOf()로 복사하기
        Map<String, Integer> copy2 = Map.copyOf(original);
        assertThat(copy2).isEqualTo(original).isNotSameAs(original);

        // ⚠️ Map.copyOf()는 변경 불가능 Map.of()와 동일하게 변경 불가능한 맵(unmodifiable map)을 반환한다.
        assertThatThrownBy(() ->
            copy2.put("C", 30)
        ).isInstanceOf(UnsupportedOperationException.class).hasMessage(null);

        // ## #3 putAll()로 복사하기
        HashMap<String, Integer> copy3 = new HashMap<>();
        copy3.putAll(original);
        assertThat(copy3).isEqualTo(original).isNotSameAs(original);

        // ## #4 Stream으로 복사하기
        Map<String, Integer> copy4 = original.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        assertThat(copy4).isEqualTo(original).isNotSameAs(original);


    }
}
