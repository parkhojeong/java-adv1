package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start();

        sleep(4000);
        myTask.runFlag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("working..");
                sleep(3000);
            }
            log("end");
        }
    }
}
