package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {
    private volatile boolean lock = false;

    public void lock() {
        log("[get lock] attempt");
        while (true) {
            if (!lock) {
                sleep(100);
                lock = true;
                break;
            } else{
                log("[get lock] fail - spin waiting");
            }
        }
        log("[get lock] complete");
    }

    public void unlock() {
        lock = false;
        log("return lock complete");
    }
}
