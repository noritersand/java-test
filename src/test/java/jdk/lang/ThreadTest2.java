package jdk.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread 테스트 두 번째
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public enum ThreadTest2 {
    ;

    @SuppressWarnings("unused")

    public static void main(String[] args) {
        ChildThread thread = new ChildThread(); // thread는 null일 수 없음
        thread.start();
        if (!thread.isAlive()) {
            throw new RuntimeException("여기 오면 안되는데");
        }

        // isAlive()는 스레드 생성 즉시 true를 반환하기 때문에 while문은 무의미함. (데몬 스레드도 아니고...)
//		while (true) {
//			if (thread.isAlive()) { // thread가 alive를 true로 반환할 때까지 무한루프
//				thread.interrupt();
//				if (!thread.isInterrupted()) {
//					throw new RuntimeException()("여기 오면 안되는데 2");
//				}
//				break;
//			}
//		}

        thread.interrupt();
        if (!thread.isInterrupted()) {
            throw new RuntimeException("여기 오면 안되는데 2");
        }
    }

    @Slf4j
    private static class ChildThread extends Thread {

        @Override
        public void run() {
            workForNothing();
        }

        @Override
        public void interrupt() {
            log.debug("im dying. :<");
            super.interrupt();
        }

        private void workForNothing() {
            log.debug("work-for-nothing: start");
            long sum = 0;
            for (int i = 0; Integer.MAX_VALUE > i; i++) {
                sum += i;
            }
            log.debug("work-for-nothing: end, sum is {}", sum);
        }
    }
}
