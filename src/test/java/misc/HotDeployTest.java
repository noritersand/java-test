package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotDeployTest {
    private static final Logger logger = LoggerFactory.getLogger(HotDeployTest.class);

    public static void main(String[] args) {
        RunMe runMe = new RunMe();
        runMe.doSomething();
    }
}

class RunMe {
    private static final Logger logger = LoggerFactory.getLogger(RunMe.class);

    public void doSomething() {
        while (true) {
            logger.info("Hello World!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
