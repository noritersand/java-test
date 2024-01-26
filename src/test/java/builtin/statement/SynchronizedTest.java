package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class SynchronizedTest {

    @Test
    void testSynchronizedMethod() {
        SynchronizedTest instance = new SynchronizedTest();

        // test #1: synchronized 메서드
        Thread first = new Thread() {
            @Override
            public void run() {
                instance.synchronizedMethod();
            }
        };
        first.start();
        Thread second = new Thread() {
            @Override
            public void run() {
                instance.synchronizedMethod();
            }
        };
        second.start();
        while (first.isAlive() || second.isAlive()) {
            // first와 second가 종료될 때까지 대기
        }
        assertEquals(10000, instance.acc);
    }

    @Test
    void testSynchronizedStatement() {
        SynchronizedTest instance = new SynchronizedTest();

        // test #2: synchronized 블럭
        Thread third = new Thread() {
            @Override
            public void run() {
                instance.synchronizedStatement();
            }
        };
        third.start();
        Thread fourth = new Thread() {
            @Override
            public void run() {
                instance.synchronizedStatement();
            }
        };
        fourth.start();
        while (third.isAlive() || fourth.isAlive()) {
            // third와 fourth가 종료될 때까지 대기
        }
        assertEquals(10000, instance.acc);
    }

    private int acc;

    public int getAcc() {
        return acc;
    }

    //	public void synchronizedMethod() {
    public synchronized void synchronizedMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.debug("=========== 시작 ================");
        log.debug("this: {}, time: {}, current method: {}(), line: {}", this, LocalDateTime.now(),
                new Throwable().getStackTrace()[0].getClassName() + "." + new Throwable().getStackTrace()[0].getMethodName(),
                new Throwable().getStackTrace()[0].getLineNumber());
        acc = 0;
        for (int i = 0; 10000 > i; ++i) {
            ++acc;
            if (acc != i + 1) {
                log.error("broken! acc: {}", acc);
                break;
            }
        }
        log.debug("=========== 끗 ================");
    }

    public void synchronizedStatement() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            log.debug("=========== 시작 ================");
            log.debug("this: {}, time: {}, current method: {}(), line: {}", this, LocalDateTime.now(),
                    new Throwable().getStackTrace()[0].getClassName() + "." + new Throwable().getStackTrace()[0].getMethodName(),
                    new Throwable().getStackTrace()[0].getLineNumber());
            acc = 0;
            for (int i = 0; 10000 > i; ++i) {
                ++acc;
                if (acc != i + 1) {
                    log.error("broken! acc: {}", acc);
                    break;
                }
            }
            log.debug("=========== 끗 ================");
        }
    }
}
