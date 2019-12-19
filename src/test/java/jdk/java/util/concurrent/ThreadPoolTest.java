package jdk.java.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadPool 테스트 유닛
 * 
 * @since 2019-12-19
 * @author noritersand
 */
public class ThreadPoolTest {
	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

	private int instanceLength = 10;

	/**
	 * 쓰레드를 여러개 생성해서 메서드를 동시 실행하는 테스트 케이스.
	 * 출력 결과를 보면 쓰레드 생성 순서와 실행 순서는 상관 없다는 것을 알 수 있다.
	 * 
	 * @author noritersand
	 */
	@Test
	public void test() {
		ThreadPoolExecutor threadPool = null;
		Collection<Callable<Integer>> threadList = null;
		int successCount = 0;
		int failCount = 0;
		int howManyThread = 10;
		try {
			List<Myclass> list = getList();
			if (list != null && list.size() > 0) {
				threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(howManyThread);
				threadList = new ArrayList<Callable<Integer>>();
				for (int i = 0; i < list.size(); i++) {
					Mythread thread = new Mythread(list.get(i));
					threadList.add(thread);
				}
				List<Future<Integer>> resultList = threadPool.invokeAll(threadList);
				for (Future<Integer> future : resultList) {
					try {
						successCount += future.get();
					} catch (Exception e) {
						failCount++;
						logger.error(e.toString());
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (threadPool != null) {
				try {
					threadPool.shutdown();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		Assert.assertTrue(instanceLength == successCount);
		Assert.assertTrue(0 == failCount);
	}
	
	private class Myclass {
		private int idx;

		public Myclass(int idx) {
			this.idx = idx;
		}
		
		@Override
		public String toString() {
			return String.valueOf(idx) + "번 째 인스턴스";
		}
	}
	
	private class Mythread implements Callable<Integer> {
		private Myclass var;

		public Mythread(Myclass myclass) {
			this.var = myclass;
		}

		/**
		 * 이 메서드는 {@link java.util.concurrent.FutureTask#run()}에서 호출한다.
		 */
		@Override
		public Integer call() throws Exception {
			return runThread();
		}
		
		public Integer runThread() {
			boolean success = true;
			
			// 두 썸띵
			logger.debug(String.valueOf(this.var));;
			
			if (success) {
				return 1; // 성공
			} 
			throw new RuntimeException(); // 패실
		}
		
	}
	
	public List<Myclass> getList() {
		List<Myclass> list = new LinkedList<Myclass>();
		for (int i = 0; i < instanceLength; ++i) {
			list.add(new Myclass(i));
		}
		return list;
	}
}
