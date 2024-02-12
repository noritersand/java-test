package testbed.work;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TryEverything {


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
