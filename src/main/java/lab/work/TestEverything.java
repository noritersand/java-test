package lab.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEverything {
    private static final Logger logger = LoggerFactory.getLogger(TestEverything.class);


}

class RunMe extends Thread {
    private void loadPage() {
        // ...
    }

    @Override
    public void run() {
        loadPage();
    }
}
