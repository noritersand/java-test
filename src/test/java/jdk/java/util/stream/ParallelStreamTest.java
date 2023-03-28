package jdk.java.util.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 병렬 Stream 테스트
 * <p>
 * required jdk8 or higher
 * <p>
 * - https://futurecreator.github.io/2018/08/26/java-8-streams/ - https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/
 *
 * @author fixalot
 * @since 2018-07-16
 */
@Slf4j
public class ParallelStreamTest {

    @Test
    public void testParallelLoopOldWay() {
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
    public void testParallelStream() {
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
    public void testParallelStream2() {
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
