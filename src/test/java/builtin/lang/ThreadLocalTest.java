package builtin.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <p>ThreadLocal 테스트 슈트</p>
 *
 * @author fixalot
 * @since 2023-06-13
 */
@Slf4j
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ThreadLocalTest {

    /**
     * <p>ThreadLocal은 쓰레드마다 독립적이다.</p>
     * <p>쓰레드가 종료되면 해당 쓰레드와 관련된 리소스들이 정리되고 메모리에서 해제되는데, 이때 ThreadLocal에 저장된 값도 함께 정리된다.</p>
     *
     * @throws InterruptedException
     */
    @Test
    void independence() throws InterruptedException {
        // ThreadLocal을 이용해 캐시 역할을 할 필드를 정의하고 초기값을 설정하는 코드.
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> {
            return "Hello?"; // threadLocal은 매 쓰레드가 종료되면(시작할 때?) 'Hello?'로 초기화된다.
        });

        Runnable runner = () -> {
            log.debug("Thread.currentThread().getId(): {}", Thread.currentThread().getId());

            String cache = threadLocal.get();
            assertThat(cache).isEqualTo("Hello?");

            threadLocal.set("My new string.");
            assertThat(threadLocal.get()).isEqualTo("My new string.");
        };

        Thread thread = new Thread(runner);
        Thread thread2 = new Thread(runner);
        Thread thread3 = new Thread(runner);

        thread.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 라이프사이클이 쓰레드에 의존하긴 하지만 하나의 쓰레드 내의 여러 인스턴스들이 값을 공유하는 것은 아님
     */
    @Test
    void instantiation() {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("qwer");

        ThreadLocal threadLocal2 = new ThreadLocal();
        assertThat(threadLocal2.get()).isNull();

        ThreadLocal threadLocal3 = ThreadLocal.withInitial(() -> null);
        assertThat(threadLocal3.get()).isNull();
    }
}
