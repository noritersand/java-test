package builtin.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Runnable}을 구체화한 클래스로 쓰레드 만드는 방법 예시
 *
 * @author noritersand
 * @since 2020-03-18
 */
@Slf4j
public enum RunnableTest {
    ;

//	@Test
//	public void test() {
//		Thread thr = new Thread(new Mythread());
//		Runnable thr = new Mythread();
//		thr.start();
//	}

    public static void main(String[] args) {
        Thread thr = new Thread(new Lazy(), "lazy one");
        thr.start();

        Thread thr2 = new Thread(new NotLazy(), "not lazy one");
        thr2.start();
    }

    @Slf4j
    private static class Lazy implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            log.debug("...");
        }
    }

    @Slf4j
    private static class NotLazy implements Runnable {

        @Override
        public void run() {
            log.debug("예쒈?");
        }
    }
}
