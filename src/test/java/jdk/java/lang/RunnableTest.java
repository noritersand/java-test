package jdk.java.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Runnable}을 구체화한 클래스로 쓰레드 만드는 방법 예시
 *
 * @author noritersand
 * @since 2020-03-18
 */
public class RunnableTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RunnableTest.class);

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

    private static class Lazy implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger(RunnableTest.Lazy.class);

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
            logger.debug("...");
        }
    }

    private static class NotLazy implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger(RunnableTest.NotLazy.class);

        @Override
        public void run() {
            logger.debug("예쒈?");
        }
    }
}
