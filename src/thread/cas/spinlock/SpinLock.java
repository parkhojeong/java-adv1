package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLock {
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("[get lock] attempt");
        while(!lock.compareAndSet(false, true)){
//            sleep(100);
            log("[get lock] fail - spin waiting");
        }
        log("[get lock] complete");
    }

    public void unlock() {
        lock.set(false);

        log("return lock complete");
    }
}
