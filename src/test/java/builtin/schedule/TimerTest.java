package builtin.schedule;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * <p>{@link Timer} 테스트 슈트</p>
 * <p>(왜인지는 정확히 모르겠으나) Timer는 별도의 쓰레드에서 실행되기 떄문에 task가 실행되기 전에 JVM이 종료될 수 있다.</p>
 * <p>따라서 {@link CompletableFuture} 혹은 {@link java.util.concurrent.CountDownLatch}를 사용해서 JVM 종료를 미뤄야 정상적인 테스트가 가능함</p>
 *
 * @author fixalot
 * @since 2023-08-09
 */
@Slf4j
public class TimerTest {

    /**
     * <p>main() 메서드에선 JVM이 먼저 죽어버리는 현상이 없음. 대신 {@link System#exit(int)}로 JVM을 직접 종료해야 함</p>
     *
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.debug("유후");
                System.exit(0);
            }
        };

        timer.schedule(task, 2000);
    }

    @Test
    public void testWithCountDownLatch() throws InterruptedException {
        Timer timer = new Timer();
        CountDownLatch latch = new CountDownLatch(1);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.debug("유후2");
                latch.countDown();
            }
        };

        timer.schedule(task, 2000);
        latch.await(); // 작업이 완료될 때까지 대기
    }

    @Test
    public void testWithCompletableFuture() throws InterruptedException, ExecutionException {
        Timer timer = new Timer();
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.debug("유후");
                completableFuture.complete(null);
            }
        };

        timer.schedule(task, 2000);
        completableFuture.get(); // 작업이 완료될 때까지 대기
    }

    @Test
    public void testFixedRate() throws InterruptedException, ExecutionException {
        Timer timer = new Timer();
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        TimerTask task = new TimerTask() {
            private int value = 0;

            @Override
            public void run() {
                log.debug("유후");
                if (++value >= 5) {
                    completableFuture.complete(null);
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 500);
        completableFuture.get(); // 작업이 완료될 때까지 대기
    }
}
