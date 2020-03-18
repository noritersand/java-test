package jdk.java.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Runnable}을 구체화한 클래스로 쓰레드 만드는 방법
 * 
 * @since 2020-03-18
 * @author noritersand
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
		Thread thr = new Thread(new LazyOne());
		thr.start();
		
		Thread thr2 = new Thread(new NotLazyOne());
		thr2.start();
	}

	private static class LazyOne implements Runnable {
		private static final Logger logger = LoggerFactory.getLogger(RunnableTest.LazyOne.class);
		
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
	
	private static class NotLazyOne implements Runnable {
		private static final Logger logger = LoggerFactory.getLogger(RunnableTest.NotLazyOne.class);
		
		@Override
		public void run() {
			logger.debug("예쒈?");
		}
	}
}
