package builtin.collection;

import testbed.dummy.generator.ListGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * java.util.Collections 테스트 슈트
 */
@Slf4j
class CollectionsTest {

    @Test
    void getEmptyList() {
        List emptyList = Collections.EMPTY_LIST; // new EmptyList<>();
        assertThat(emptyList.size()).isEqualTo(0);

        // 위와 같음
        List emptyList2 = Collections.emptyList(); // new EmptyList<>();
        assertThat(emptyList2.size()).isEqualTo(0);
    }

    @Test
    void testMin() {
        List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
        assertThat(Collections.min(list)).isEqualTo(1);
    }

    @Test
    void testMax() {
        List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
        assertThat(Collections.max(list)).isEqualTo(99);
        list.get(0);
    }

    @Test
    void testSort() {
        Integer[] chaos = {105, 99, 2, 44, 73, 99};
        List<Integer> list = Arrays.asList(chaos);

        Integer[] ascSortComparison = {2, 44, 73, 99, 99, 105};
        Integer[] descSortComparison = {105, 99, 99, 73, 44, 2};

        // 그냥 호출하면 오름차순
        Collections.sort(list);
        assertThat(list.toArray()).isEqualTo(ascSortComparison);

        // 내림차순
        Collections.sort(list, Collections.reverseOrder());
        assertThat(list.toArray()).isEqualTo(descSortComparison);

        // Comparator 직접 작성하기: 오름차순
        list = Arrays.asList(chaos);
        // 아래 줄은 요렇게 쓴 거랑 같음:
		/*
		Collections.sort(list, (o1, o2) -> {
		  return o1 - o2;
		});
		*/
        Collections.sort(list, (o1, o2) -> o1 - o2);
        // 음수를 반환하면 o1을 좌측으로, 0을 반환하게 하면 그대로 유지, 양수를 반환하면 o1을 우측으로 정렬한다.
        // o1에서 o2를 뺄 때, 음수가 되는 경우는 o1이 o2보다 작을 때다.
        // 그래서 작은 값이 좌측으로 정렬되는 오름차순 정렬이 된다.
        assertThat(list.toArray()).isEqualTo(ascSortComparison);

        // Comparator 직접 작성하기: 내림차순
        list = Arrays.asList(chaos);
        Collections.sort(list, (o1, o2) -> o2 - o1);
        // 음수: o1을 좌측으로, 0: 유지, 양수: 01을 우측으로
        // 이 경우 음수가 반환되려면 o1이 o2보다 커야하므로 큰 값이 좌측으로 정렬되는 내림차순 정렬이다.
        assertThat(list.toArray()).isEqualTo(descSortComparison);
    }

    /**
     * 정렬 알고리즘 중 가장 빠르다는 이진 탐색 테스트. 빠르긴 한데 정렬이 불가능한 데이터에는 사용할 수 없는 한계가 있다.<p>
     * <p>
     * 경우에 따라 일반 탐색이 더 빠를 수도 있으나 일정한 결과를 보장하는 것은 아님.(eg. 정렬했더니 우연히도 찾으려는 데이터가 앞 쪽에 있다면?)<p>
     * <p>
     * 중요: binarySearch()를 사용하기 전에 리스트를 반드시 정렬해야 함. 안그러면 (-(리스트의 길이) - 1)이 반환됨. (eg. 길이가 10이면 -11)<p>
     * <p>
     * 정작 찾는 것보다 정렬이 더 오래 걸림:
     * <ul>
     *     <li>10만개의 문자열 정렬: 102,815,600 나노초</li>
     *     <li>정렬 후 찾기까지: 67,400 나노초</li>
     *     <li>45123 인덱스의 문자열을 순차 탐색: 4,995,501 나노초</li>
     * </ul>
     */
    @Test
    void testBinarySearch() {
        final String findMe = "날찾아봐요요러케";
        List<String> list = ListGenerator.generateStringList(100000, 45123, findMe);

        // 만약 그냥 처음부터 찾는다면 경과 시간은?
        StopWatch watch = new StopWatch();
        watch.start(); // 시작

        Collections.sort(list);

        watch.suspend();
        long afterSortingTime = watch.getNanoTime();
        log.debug("sorting time: {}", afterSortingTime);
        watch.resume();

        int found = Collections.binarySearch(list, findMe);

        watch.stop();
        long afterSearchTime = watch.getNanoTime();
        log.debug("pure searching time: {}", afterSearchTime - afterSortingTime);

        assertThat(list.get(found)).isEqualTo(findMe);
    }

    @Test
    void testPlanSearch() {
        final String findMe = "날찾아봐요요러케";
        List<String> list = ListGenerator.generateStringList(100000, 45123, findMe);

        // 만약 그냥 처음부터 찾는다면 경과 시간은?
        StopWatch watch = new StopWatch();
        watch.start(); // 시작

        int found = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(findMe)) {
                found = i;
                break;
            }
        }

        watch.stop();
        log.debug("searching time: {}", watch.getNanoTime());

        assertThat(list.get(found)).isEqualTo(findMe);
    }

    /**
     * <a href="http://www.angelikalanger.com/GenericsFAQ/FAQSections/ParameterizedTypes.html#WIldcard+Instantiations">Generic And Parameterized Types</a>
     *
     * @author noritersand
     */
    @Test
    void testWildcardParameterizedType() {
        List<? super String> strList = Arrays.asList("a", "b");
        assertThat(strList.contains("a")).isTrue();
    }

    @Test
    void howToAvoidNpeWithForStatement() {
        List<String> texts = null;
        for (String s : npeSafe(texts)) {
            log.debug("s: {}", s);
        }
    }

    private Iterable<? extends String> npeSafe(List<String> texts) {
        return texts == null ? Collections.emptyList() : texts;
    }

    /**
     * <p>List.copyOf()와 다르게 변경 가능한 복제본을 반환한다.</p>
     * <p>근데 이렇게 불편하게 써서야... 그냥 생성자 함수로 하는 게 낫겠는데?</p>
     */
    @Test
    void testCopy() {
        List<String> original = new ArrayList<>();
        original.add("Java");
        original.add("Python");

        List<String> copy = Arrays.asList(new String[original.size()]);

        Collections.copy(copy, original);
        assertThat(copy).isEqualTo(original);
    }
}
