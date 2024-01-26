package builtin.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * ThreadGroup 사용법 테스트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class ThreadGroupTest {

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
            if (null == parentGroup) {
                log.debug("parent group is null");
                break;
            } else {
                log.debug("parent group is not null");
                threadGroup = parentGroup;
            }
        }
        Thread[] threads = new Thread[256];
        threadGroup.enumerate(threads);
        log.debug("{}", threadGroup.activeCount());
    }
}
