package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new ParkTest(), "t1");
        thread.start();

        sleep(100);
        log("t1 state = " + thread.getState());
    }

    static class ParkTest implements Runnable {
        @Override
        public void run() {
            log("park start");
            LockSupport.parkNanos(1000_000000);
            log("park end, state = " + Thread.currentThread().getState());
            log("interrupted = " + Thread.currentThread().isInterrupted());
        }
    }
}
