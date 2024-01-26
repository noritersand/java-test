package builtin.stream;

import com.google.common.base.Predicate;
import testbed.dummy.generator.ListGenerator;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Stream 클래스 테스트
 * <p>
 * required jdk8 or higher
 * <p>
 * - https://futurecreator.github.io/2018/08/26/java-8-streams/ - https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/
 *
 * @author fixalot
 * @since 2018-07-16
 */
@Slf4j
class StreamTest {

    /**
     * Stream.forEach() 테스트<br>
     * forEach로 한 바퀴 돌면 스트림이 닫혔거나 작업이 끝난걸로 간주되어 다시 스트림을 사용할 수 없나보다.
     *
     * @throws IOException
     * @author fixalot
     */
    @Test
    void testForEach() throws IOException {
        List<Integer> list = Arrays.asList(1, 3, 7);
        Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);
        assertThatThrownBy(() -> {
            stream.forEach(System.out::println);
        }).isInstanceOf(IllegalStateException.class);
        stream.close(); // 생략 가능

        Stream<Integer> stream2 = list.stream();
        List<Integer> basket = new ArrayList<Integer>();
        stream2.forEach((k) -> {
            log.debug("ele: {}", k.toString());
            basket.add(k);
        });
        stream2.close();
        assertThat(basket.toArray(new Integer[list.size()])).isEqualTo(new Integer[]{1, 3, 7});
        assertThat(basket.size()).isEqualTo(3);
    }

    @Test
    void testFilter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // 짝수 찾기
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return 0 == input % 2;
            }
        };

        // 필터링 결과 모두 출력
        Stream<Integer> stream = list.stream();
        List<Integer> result1 = stream.filter(predicate).collect(Collectors.toList());
        assertThat(result1).isEqualTo(Arrays.asList(2, 4, 6, 8));

        // 필터링 결과 중 첫 번째
        stream = list.stream();
        Integer result2 = stream.filter(predicate).findFirst().get();
        assertThat(result2.intValue()).isEqualTo(2);

        // 필터링 결과 중 아무거나?? 그냥 첫 번째꺼 나오는거 같은데...
        stream = list.stream();
        Integer result3 = stream.filter(predicate).findAny().get();
        log.debug("{}", result3);
    }

    /**
     * 합계 구할 때 많이 쓰는 reduce() 테스트
     */
    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> reduce = list.stream().reduce((a, b) -> a + b);
        assertThat(reduce).isEqualTo(45);
    }

    @Test
    void testMax() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int max = list.stream().max(Integer::compareTo).get();
        assertThat(max).isEqualTo(9);
    }

    @Test
    void testSorted() {
        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        // List<String> sortedList = list.stream().sorted((o1,o2)-> o2.compareTo(o1)).collect(Collectors.toList());
        // 위처럼 쓴거랑 같음
        List<String> sortedList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertThat(sortedList).isEqualTo(Arrays.asList("c", "a", "Z", "Y", "B", "A", "9", "4", "1"));
    }

    @Test
    void testAnyMatch() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> stream = list.stream();
        boolean result = stream.anyMatch(e -> {
            return 10 < e;
        });
        assertThat(result).isFalse(); // list에는 10보다 큰게 없음.

        String[] arr = {"/findme", "/beginning", "/start"};
        final String compareme = "/beginning/list/hello";
        Stream<String> stream2 = Arrays.asList(arr).stream();
        boolean existsForeMatch = stream2.anyMatch(e -> 0 == compareme.indexOf(e));
        assertThat(existsForeMatch).isTrue();
    }

    @Test
    void testMap() {
        List<ListGenerator.Obj> objList = ListGenerator.generateObjList(10);
        log.debug("objList: {}", objList);
        List<Integer> nums = objList.stream().map(ele -> ele.getNum()).collect(Collectors.toList());
        log.debug("nums: {}", nums);
        assertThat(nums.size()).isEqualTo(10);
        assertThat(nums.get(1)).isEqualTo(1);
    }

    /**
     * stream의 distinct() 테스트
     */
    @Test
    void testDistinct() {
        List<ListGenerator.Obj> originalList = ListGenerator.generateObjList(10);
        ListGenerator.Obj b = new ListGenerator.Obj(200, "b");
        originalList.add(b);
        originalList.add(b);
        originalList.add(b);

        assertThat(originalList.size()).isEqualTo(13);
        List<ListGenerator.Obj> newList = originalList.stream().distinct().collect(Collectors.toList());
        assertThat(newList.size()).isEqualTo(11);
        // 당연히 같은 객체는 중복 제거 대상

        // 만약 객체는 다른데 필드의 값만 같다면?
        newList.add(new ListGenerator.Obj(100, "a"));
        newList.add(new ListGenerator.Obj(100, "a"));
        newList.add(new ListGenerator.Obj(100, "a"));

        assertThat(newList.size()).isEqualTo(14);
        List<ListGenerator.Obj> newList2 = newList.stream().distinct().collect(Collectors.toList());
        assertThat(newList2.size()).isEqualTo(12);
        // 어떤 원리인진 몰라도 이것도 중복 제거 잘됨
    }

    @Test
    void testParallelLoopOldWay() {
        List<String> list = new ArrayList<String>(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08"));
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                log.debug("testParallelLoopOldWay Starting:{}, element={}, ended at {}", Thread.currentThread().getName(), element, LocalDateTime.now());
            });
        }
        executor.shutdown();
    }

    /**
     * JavaScript의 async 처럼 내가 작성한 코드가 분할 처리 되는 게 아니라 내부에서 처리방식이 병렬로 수행되어 성능 향상 가능성이 있는 것 쯤이라 생각하면 되겠다.
     */
    @Test
    void testParallelStream() {
        List<String> list = List.of("하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟");
        log.debug("{}", "누가 먼저 보일까요");
        list.parallelStream().forEach(element -> {
            log.debug("First testParalelStream:{}, element: {}", Thread.currentThread().getName(), element);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        });
        list.parallelStream().forEach(element -> {
            log.debug("Second testParallelStream:{}, element: {}", Thread.currentThread().getName(), element);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        });
        log.debug("{}", "몰루?");
    }

    /**
     * 체이닝으로 이어지는 메서드들이 병렬로 도는건지 테스트하려 했더니 로그가 안찍히네
     */
    @Test
    void testParallelStream2() {
        List<String> list = List.of("하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟");
        log.debug("{}", "누가 먼저 보일까요 #2");
        list.parallelStream().filter(element -> {
            log.debug("First testParalelStream:, element: {}");
            return true;
        }).filter(element -> {
            log.debug("second testParalelStream:, element: {}");
            return true;
        });
        log.debug("{}", "몰루?");
    }
}
