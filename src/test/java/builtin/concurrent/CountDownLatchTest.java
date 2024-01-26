package builtin.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * java.util.CountDownLatch 테스트
 *
 * @author fixalot
 * @since 2018-01-24
 */
@Slf4j
class CountDownLatchTest {

    /**
     * 쓰레드 동기화에 사용되는 클래스라는데 도통 어떻게 쓰는건지...
     * <a href="http://sjava.net/2013/07/%EC%9E%90%EB%B0%94-countdownlatch%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%86%92%EC%9D%B4%EA%B8%B0/">http://sjava.net/2013/07/%EC%9E%90%EB%B0%94-countdownlatch%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%86%92%EC%9D%B4%EA%B8%B0/</a>
     *
     * @throws InterruptedException
     * @author fixalot
     */
    @Test
    void printElapsedTime() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
//		boolean success = lacth.await(2000, TimeUnit.MILLISECONDS);
//		assertTrue(success);

        long start = System.currentTimeMillis();
        for (long i = 0; 10 > i; i++) {
            new Thread(new Worker(latch)).start();
        }

        latch.await();
        long elapsedTime = System.currentTimeMillis() - start;
        log.debug("testCountDownLatch elapsed time -> {}", elapsedTime);
    }

    /**
     * Job 쓰레드
     */
    private static class Worker implements Runnable {
        private final CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                if (null == latch)
                    return;

                latch.countDown();
            }
        }
    }
}
