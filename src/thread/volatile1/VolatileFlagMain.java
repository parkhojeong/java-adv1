package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("runFlag=" + task.runFlag);
        t.start();

        sleep(1000);
        task.runFlag = false;
        log("runFlag=" + task.runFlag);
        log("main end");
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task start");
            while (runFlag) {

            }
            log("task end");
        }
    }
}
