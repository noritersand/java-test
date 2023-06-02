package misc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotDeployTest {

    public static void main(String[] args) {
        RunMe runMe = new RunMe();
        runMe.doSomething();
    }
}

@Slf4j
class RunMe {

    public void doSomething() {
        while (true) {
            log.info("Hello World!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
