package jdk.statement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class SynchronizedTest {
    private static final Logger logger = LoggerFactory.getLogger(SynchronizedTest.class);

    @Test
    public void testSynchronizedMethod() {
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
        assertEquals(10000, instance.getAcc());
    }

    @Test
    public void testSynchronizedStatement() {
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
        assertEquals(10000, instance.getAcc());
    }

    private int acc = 0;

    public int getAcc() {
        return acc;
    }

    //	public void synchronizedMethod() {
    public synchronized void synchronizedMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("=========== 시작 ================");
        logger.debug("this: {}, time: {}, current method: {}(), line: {}", String.valueOf(this), LocalDateTime.now(),
                new Throwable().getStackTrace()[0].getClassName() + "." + new Throwable().getStackTrace()[0].getMethodName(),
                new Throwable().getStackTrace()[0].getLineNumber());
        acc = 0;
        for (int i = 0; i < 10000; ++i) {
            ++acc;
            if (acc != i + 1) {
                logger.error("broken! acc: {}", acc);
                break;
            }
        }
        logger.debug("=========== 끗 ================");
    }

    public void synchronizedStatement() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
            logger.debug("=========== 시작 ================");
            logger.debug("this: {}, time: {}, current method: {}(), line: {}", String.valueOf(this), LocalDateTime.now(),
                    new Throwable().getStackTrace()[0].getClassName() + "." + new Throwable().getStackTrace()[0].getMethodName(),
                    new Throwable().getStackTrace()[0].getLineNumber());
            acc = 0;
            for (int i = 0; i < 10000; ++i) {
                ++acc;
                if (acc != i + 1) {
                    logger.error("broken! acc: {}", acc);
                    break;
                }
            }
            logger.debug("=========== 끗 ================");
        }
    }
}
