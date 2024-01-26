package builtin.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ThreadPool 테스트<br>
 * {@link Mythread} 클래스가 중요하며 홀수/짝수는 큰 의미 없다.
 *
 * @author noritersand
 * @since 2019-12-19
 */
@Slf4j
class ThreadPoolTest {

    private final int instanceLength = 10;

    /**
     * 쓰레드를 여러개 생성해서 메서드를 동시 호출하는 테스트 케이스.<br>
     * 출력 결과를 보면 쓰레드 생성 순서와 호출 순서는 상관 없다는 것을 알 수 있다.
     */
    @Test
    void test() {
        ThreadPoolExecutor threadPool = null;
        Collection<Callable<Integer>> threadList = null;
        int successCount = 0;
        int howManyThread = 10;
        try {
            List<Myclass> list = getList();
            if (null != list && 0 < list.size()) {
                threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(howManyThread);
                threadList = new ArrayList<Callable<Integer>>();
                for (int i = 0; i < list.size(); i++) {
                    Mythread thread = new Mythread(list.get(i));
                    threadList.add(thread);
                }
                // invokeAll: threadList를 threadPool에 등록하고 모두 호출한다.
                // resultList: 쓰레드 실행 결과. 이 예시에서는 Mythread.call()의 반환값이 할당되어 있음.
                List<Future<Integer>> resultList = threadPool.invokeAll(threadList);
                for (Future<Integer> future : resultList) {
                    successCount += future.get(); // future.get() 자바독에 따르면 이 메서드를 호출하는 시점에 아직 쓰레드가 실행중일 수도 있다.
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (null != threadPool) {
                try {
                    threadPool.shutdown();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        assertEquals(getEvenLength(), successCount); // 짝수만큼 성공
        assertEquals(getOddLength(), this.instanceLength - successCount); // 홀수만큼 실패
    }

    /**
     * instanceLength개 짜리 리스트 반환하는 메서드
     */
    public List<Myclass> getList() {
        List<Myclass> list = new LinkedList<Myclass>();
        for (int i = 0; this.instanceLength > i; ++i) {
            list.add(new Myclass(i));
        }
        return list;
    }

    /**
     * 쓰레드가 실행할 {@link Callable}의 구현체. 중요.<br>
     * {@link ThreadPoolTest}에서만 참조하고 있어서 private으로 설정함.
     */
    private class Mythread implements Callable<Integer> {
        private final Myclass var;

        public Mythread(Myclass myclass) {
            this.var = myclass;
        }

        /**
         * 쓰레드로 실행할 내용이 위치하는 메서드. 이 메서드는 {@link java.util.concurrent.FutureTask#run()}에서 호출한다.
         */
        @Override
        public Integer call() throws Exception {
            // 두 썸띵
            int idx = this.var.getIdx();
            log.debug("{}번째 인스턴스", idx);

            // 짝수는 성공, 홀수는 실패하게 함
            if (0 == idx % 2) {
                return 1; // 성공
            } else {
                return 0; // 패실
            }
//			throw new RuntimeException(); // 굳이 예외를 발생시킬 필요가 있나?
        }
    }

    /**
     * 그냥 POJO
     */
    private class Myclass {
        private final int idx;

        public Myclass(int idx) {
            this.idx = idx;
        }

        public int getIdx() {
            return idx;
        }
    }

    /**
     * 짝수 몇 개?
     */
    private int getEvenLength() {
        int length = 0;
        for (int i = 0; instanceLength > i; ++i) {
            length += 0 == i % 2 ? 1 : 0;
        }
        return length;
    }

    /**
     * 홀수 몇 개?
     */
    private int getOddLength() {
        int length = 0;
        for (int i = 0; instanceLength > i; ++i) {
            length += 0 == i % 2 ? 1 : 0;
        }
        return length;
    }
}
