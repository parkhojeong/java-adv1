package util;

import static util.MyLogger.log;

public abstract class ThreadUtils {

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            log(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
