package jdk.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Thread 테스트
 *
 * @author fixalot
 * @since 2017-07-05
 */
@Slf4j
public class ThreadTest1 {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.debug("I'm main!");
            }
        };
        thread.start();
    }

    @Test
    public void getCurrentMethodName() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        // System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName(); //Wrong, fails for depth = 0
//		return ste[ste.length - 1 - depth].getMethodName(); // Thank you Tom Tresansky

        log.debug(ste[ste.length - 1].getMethodName());
        log.debug(ste[ste.length - 2].getMethodName());
        log.debug(ste[ste.length - 3].getMethodName());
        log.debug(ste[ste.length - 4].getMethodName());
        log.debug(ste[ste.length - 5].getMethodName());

//		log.debug(ste[ste.length - 1 - depth].getMethodName());
//		log.debug(ste[ste.length - 1 - depth].getMethodName());
    }
}
