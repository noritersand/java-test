package builtin.schedule;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * <p>{@link java.util.concurrent.ScheduledExecutorService} 테스트 슡</p>
 * <p>{@link java.util.Timer}와 마찬가지로 별도의 쓰레드에서 실행되기 떄문에 task가 실행되기 전에 JVM이 종료될 수 있다.</p>
 * <p>따라서 {@link CompletableFuture} 혹은 {@link java.util.concurrent.CountDownLatch}를 사용해서 JVM 종료를 미뤄야 정상적인 테스트가 가능함</p>
 *
 * @author fixalot
 * @since 2023-08-09
 */
@Slf4j
class ScheduledExecutorServiceTest {

    /**
     * <p>{@link java.util.Timer}처럼 main() 메서드에선 JVM이 먼저 종료되지 않음</p>
     * @param args
     */
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            private int value = 0;

            @Override
            public void run() {
                log.debug("{}", "유후!");
                if (++value >= 3) {
                    // 3번 반복하고 종료
                    executor.shutdown();
                }
            }
        };
        executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS); // 1초 후부터 2초 간격으로 실행
    }

    /**
     * {@link ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit)}는 이전 작업이 시작된 시간부터 period만큼 기다린 후 다음 작업을 실행한다.
     */
    @Test
    void testScheduleAtFixedRate() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            private int value = 0;

            @Override
            public void run() {
                log.debug("{}", "유후!");
                if (++value >= 3) {
                    // 3번 반복하고 종료
                    executor.shutdown();
                    completableFuture.complete(null);
                }
            }
        };
        // 1초 후부터 2초마다 실행
        executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        completableFuture.get(); // 작업이 완료될 때까지 대기
    }

    /**
     * {@link ScheduledExecutorService#scheduleWithFixedDelay(Runnable, long, long, TimeUnit)}는 이전 작업이 종료된 시간부터 period만큼 기다린 후 다음 작업을 실행한다.
     */
    @Test
    void testScheduleWithFixedDelay() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            private int value = 0;

            @Override
            public void run() {
                log.debug("{}", "유후!");
                if (++value >= 3) {
                    // 3번 반복하고 종료
                    executor.shutdown();
                    completableFuture.complete(null);
                }
            }
        };
        // 1초 후부터 이전 작업이 종료된 시간으로부터 2초가 지나면 실행
        executor.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);

        completableFuture.get(); // 작업이 완료될 때까지 대기
    }
}
