package jdk.java.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadPool 테스트 유닛<br>
 * {@link Mythread} 클래스가 중요하며 홀수/짝수는 큰 의미 없다.
 * 
 * @since 2019-12-19
 * @author noritersand
 */
public class ThreadPoolTest {
	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

	private int instanceLength = 10;

	/**
	 * 쓰레드를 여러개 생성해서 메서드를 동시 호출하는 테스트 케이스.<br>
	 * 출력 결과를 보면 쓰레드 생성 순서와 호출 순서는 상관 없다는 것을 알 수 있다.
	 * 
	 * @author noritersand
	 */
	@Test
	public void test() {
		ThreadPoolExecutor threadPool = null;
		Collection<Callable<Integer>> threadList = null;
		int successCount = 0;
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
				// invokeAll: threadList를 threadPool에 등록하고 모두 호출한다.
				// resultList: 쓰레드 실행 결과. 이 예시에서는 Mythread.call()의 반환값이 할당되어 있음.
				List<Future<Integer>> resultList = threadPool.invokeAll(threadList);
				for (Future<Integer> future : resultList) {
					successCount += future.get(); // future.get() 자바독에 따르면 이 메서드를 호출하는 시점에 아직 쓰레드가 실행중일 수도 있음.
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
		Assert.assertTrue(getEvenLength() == successCount); // 짝수만큼 성공
		Assert.assertTrue(getOddLength() == this.instanceLength - successCount); // 홀수만큼 실패
	}

	/**
	 * 그냥 instanceLength개 짜리 리스트 반환하는 메서드
	 * 
	 * @return
	 */
	public List<Myclass> getList() {
		List<Myclass> list = new LinkedList<Myclass>();
		for (int i = 0; i < instanceLength; ++i) {
			list.add(new Myclass(i));
		}
		return list;
	}
	
	/**
	 * 쓰레드가 실행할 {@link Callable}의 구현체. 중요.<br>
	 * {@link ThreadPoolTest}에서만 참조하고 있어서 private으로 설정함.
	 */
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

		/**
		 * 쓰레드로 실행할 내용이 위치하는 메서드.
		 * 
		 * @return
		 * @author noritersand
		 */
		public Integer runThread() {
			// 두 썸띵
			final int idx = this.var.getIdx();
			logger.debug(String.valueOf(idx) + "번 째 인스턴스");

			// 짝수는 성공, 홀수는 실패하게 함
			if (idx % 2 == 0) {
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
		private int idx;

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
		for (int i = 0; i < this.instanceLength; ++i) {
			length += i % 2 == 0 ? 1 : 0;
		}
		return length;
	}
	
	/**
	 * 홀수 몇 개?
	 */
	private int getOddLength() {
		int length = 0;
		for (int i = 0; i < this.instanceLength; ++i) {
			length += i % 2 == 0 ? 1 : 0;
		}
		return length;
	}
}
