package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start();

        sleep(4000);
        thread.interrupt();
        log("thread.isInterrupted() =" + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("working..");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("thread.isInterrupted()=" + Thread.currentThread().isInterrupted());
                log("message=" + e.getMessage());
                log("state=" + Thread.currentThread().getState());
            }
            log("end");
        }
    }
}
