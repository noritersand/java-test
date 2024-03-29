package builtin.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link ArrayList} 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class ArrayListTest {

    @Test
    void testAdd() {
        List<Object> list = new ArrayList<>();
        list.add(null);
        assertThat(list).hasSize(1);
    }

    @Test
    void cloneManual() {
        ArrayList<ListTestModel> origins = new ArrayList<>();
        origins.add(new ListTestModel("123"));
        origins.add(new ListTestModel("456"));
        origins.add(new ListTestModel("789"));

        ArrayList<ListTestModel> newbies = new ArrayList<>();
        for (ListTestModel ele : origins) {
            newbies.add(new ListTestModel(ele.getName()));
        }

        assertThat(newbies.get(0)).isNotEqualTo(origins.get(0));
        assertThat(newbies.get(0)).isNotEqualTo(origins.get(0));
    }

    @Test
    void getSize() {
        ArrayList<String> stringList = new ArrayList<>();
        assertThat(stringList.size()).isEqualTo(0);
        stringList = new ArrayList<>(10); // 리스트의 capacity를 지정한다. size가 아니다.
        assertThat(stringList.size()).isEqualTo(0); // capacity 지정과 size는 관련 없음.
    }

    /**
     * 리스트 안의 특정 요소 삭제하기 #1
     *
     * @author fixalot
     */
    @Test
    void removeElementWithIterator() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String s = iter.next(); // 반드시 remove() 전에 호출되어야 함.
            if ("a".equals(s)) {
                iter.remove();
            }
        }
        assertThat(list).isEqualTo(Arrays.asList("b", "c", "d"));
        assertThat(list.size()).isEqualTo(3);
    }

    /**
     * 리스트 안의 특정 요소 삭제하기 #2
     *
     * @author fixalot
     */
    @Test
    void removeElementByIndex() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if ("a".equals(str)) {
                list.remove(i);
                i--;
            }
        }
        assertThat(list).isEqualTo(Arrays.asList("b", "c", "d"));
        assertThat(list.size()).isEqualTo(3);
    }

    /**
     * 리스트 안의 특정 요소 삭제하기 #3
     *
     * @author fixalot
     */
    @Test
    void removeElementInWhile() {
        String[] strs = {"a", "b", "c", "d", "e"};

        // 앞 3개 지우기
        List<String> list = Arrays.stream(strs).collect(Collectors.toList());
        {
            int cnt = 0;
            while (true) {
                if (3 == cnt) {
                    break;
                }
                list.remove(0);
                cnt++;
            }
        }
        assertThat(list.toString()).isEqualTo("[d, e]");

        // 뒤 3개 지우기
        list = Arrays.stream(strs).collect(Collectors.toList());
        for (int cnt = 0, i = list.size(); 0 <= i; i--) {
            if (3 > cnt) {
                list.remove(list.size() - 1);
                i++;
                cnt++;
            }
        }
        assertThat(list.toString()).isEqualTo("[a, b]");
    }
//	@Test
//	public void removeElementWithStream() {
//		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
//		list.stream().set

//	}

    /**
     * 리스트 검색 테스트 1: for문으로 전체 검색
     */
    @Test
    void search() {
        Integer[] values = {1, 3, 7};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
        final int targetValue = 3;
        int targetIndex = 99;

        for (int i = 0; i < list.size(); i++) {
            if (targetValue == list.get(i)) {
                targetIndex = i;
                break;
            }
        }
        assertThat(targetIndex).isEqualTo(1);
    }

    /**
     * 리스트 검색 테스트 3: java8의 StreamAPI 사용
     */
    @Test
    void searchWithStream() {
        List<HashMap<String, Object>> list = getSomeList();
        List<HashMap<String, Object>> searchResult
                = list.stream().filter(ele -> "b".equals(ele.get("key")) || "d".equals(ele.get("key"))).collect(Collectors.toList());
        assertThat(list.size()).isEqualTo(4); // 원래 리스트는 변하지 않음
        assertThat(searchResult.size()).isEqualTo(2); // filter의 조건에 맞는 새로운 리스트의 길이
        assertThat(searchResult.get(0).get("value")).isEqualTo("456");
        assertThat(searchResult.get(1).get("value")).isEqualTo("012");
    }

    @Test
    void testToArray() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(123);
        list.add(234);
        list.add(345);
        assertThat(list.toArray(new Integer[]{}))
                .isEqualTo(new Integer[]{123, 234, 345});
    }

    @Test
    void fromArray() {
        Integer[] values = {1, 3, 7};
        List<Integer> list = new ArrayList<>(Arrays.asList(values));
        assertThat(list).isEqualTo(Arrays.asList(1, 3, 7));

        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        assertThat(list2).isEqualTo(Arrays.asList("a", "b", "c"));
    }

    @Test
    void fromArrayByStream() {
        String[] strs = {"a", "b", "c", "d", "e"};
        List<String> stringList = Arrays.stream(strs).collect(Collectors.toList());
        assertThat(stringList.toString()).isEqualTo("[a, b, c, d, e]");

        int[] spam = {1, 2, 3};
        List<Integer> integerList = Arrays.stream(spam).boxed().collect(Collectors.toList());
        assertThat(integerList.toString()).isEqualTo("[1, 2, 3]");
    }

    @Test
    void testClear() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThat(list).isNotNull();
        list.clear();
        assertThat(list).isNotNull();
    }

    @Test
    void arrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        list.remove(1);

        assertThat(list.toString()).isEqualTo("[9, 7, 6, 5, 4, 3, 2, 1]");
    }

    /**
     * sublist 테스트. substring처럼 인덱스 범위에 해당하는 요소를 추출한다.
     *
     * @author fixalot
     */
    @Test
    void testSublist() {
        List<Integer> numbers = Arrays.asList(5, 3, 1, 2, 9, 5, 0, 7);

        List<Integer> firstBorn = numbers.subList(0, 1); // 5
        assertThat(firstBorn.size()).isEqualTo(1);
        assertThat(firstBorn).isEqualTo(List.of(5));

        List<Integer> head = numbers.subList(0, 4); // 5, 3, 1, 2
        assertThat(head.size()).isEqualTo(4);
        assertThat(head).isEqualTo(Arrays.asList(5, 3, 1, 2));

        List<Integer> tail = numbers.subList(4, numbers.size()); // 9, 5, 0, 7
        assertThat(tail.size()).isEqualTo(4);
        assertThat(tail).isEqualTo(Arrays.asList(9, 5, 0, 7));
    }

    @Test
    void testJoin() {
        List<String> texts = Arrays.asList("a", "b", "c");
        assertThat(String.join(", ", texts)).isEqualTo("a, b, c");
    }

    @Test
    void testForEach() {
        Integer[] values = {1, 3, 7};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
        list.forEach(k -> {
            log.debug("ele: {}", k);
        });
    }

    @Test
    void testRemoveif() {
        Integer[] values = {1, 3, 7};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));
        list.removeIf(p -> 3 == p);
        assertThat(list.toString()).isEqualTo("[1, 7]");
    }

    private ArrayList<HashMap<String, Object>> getSomeList() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", "a");
        map.put("value", "123");
        list.add(map);
        map = new HashMap<>();
        map.put("key", "b");
        map.put("value", "456");
        list.add(map);
        map = new HashMap<>();
        map.put("key", "c");
        map.put("value", "789");
        list.add(map);
        map = new HashMap<>();
        map.put("key", "d");
        map.put("value", "012");
        list.add(map);
        return list;
    }
    @SuppressWarnings("unused")
    private class ListTestModel {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public ListTestModel(String name) {
            this.name = name;
        }

    }

    @Test
    void copyListToAnotherList() {
        // 원본
        List<String> original = new ArrayList<>();
        original.add("Java");
        original.add("Python");

        // #1 생성자로 복사하기
        ArrayList<String> copy = new ArrayList<>(original);
        assertThat(copy).isEqualTo(original).isNotSameAs(original);

        // ⚠️ null로는 초기화할 수 없음
        assertThatThrownBy(() ->
            new ArrayList<String>(null)
        ).isInstanceOf(NullPointerException.class).hasMessage("Cannot invoke \"java.util.Collection.toArray()\" because \"c\" is null");

        // ⚠️ 복제가 아니라 얕은 복사라서, 내부의 객체는 같은 인스턴스를 바라본다.
        ArrayList<ListTestModel> origins1 = new ArrayList<>();
        origins1.add(new ListTestModel("123"));
        ArrayList<ListTestModel> imNotClone = new ArrayList<>(origins1);
        assertThat(imNotClone.get(0)).isSameAs(origins1.get(0));

        // ArrayList 내부의 배열 객체는 어떻게 다뤄질까?
        ArrayList<Integer> intArr = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> origins2 = new ArrayList<>(intArr);
        ArrayList<Integer> imNotClone2 = new ArrayList<>(origins2);
        assertThat(origins2).isEqualTo(intArr).isNotSameAs(intArr); // intArr을 복사해서 값은 동등하지만 (당연하지만) 서로 다른 인스턴스다.
        assertThat(origins2.toArray(new Integer[]{})).isNotSameAs(intArr.toArray(new Integer[]{})); // toArray()는 내부에서 새 배열을 반환하기 때문에 서로 일치하지 않는다.
        assertThat(imNotClone2.toArray(new Integer[]{})).isNotSameAs(origins2.toArray(new Integer[]{})); // toArray()는 내부에서 새 배열을 반환하기 때문에 서로 일치하지 않는다.

        // #2 addAll()로 복사하기
        ArrayList<String> copy2 = new ArrayList<>();
        copy2.addAll(original);
        assertThat(copy2).isEqualTo(original).isNotSameAs(original);

        // ⚠️ 이것도 null은 예외 발생함
        assertThatThrownBy(() ->
            copy2.addAll(null)
        ).isInstanceOf(NullPointerException.class).hasMessage("Cannot invoke \"java.util.Collection.toArray()\" because \"c\" is null");
    }
}
