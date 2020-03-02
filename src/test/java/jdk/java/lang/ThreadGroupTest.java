package jdk.java.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadGroup 사용법 테스트
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class ThreadGroupTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ThreadGroupTest.class);

	/**
	 * TOOD 어떻게 쓰는건지 몰겄다.
	 * 
	 * @param args
	 * @author fixalot
	 */
	public static void main(String[] args) {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		while (true) {
			ThreadGroup parentGroup = threadGroup.getParent();
			if (parentGroup == null) {
				logger.debug("parent group is null");
				break;
			} else {
				logger.debug("parent group is not null");
				threadGroup = parentGroup;
			}
		}
		Thread[] threads = new Thread[256];
		threadGroup.enumerate(threads);
		logger.debug("{}", threadGroup.activeCount());
	}
}
