package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start();

        sleep(10);
        thread.interrupt();
        log("[1] thread.isInterrupted()=" + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while(!Thread.interrupted()) {
                log("working..");
            }

            log("[2] thread.isInterrupted()=" + Thread.currentThread().isInterrupted());
            log("state=" + Thread.currentThread().getState());

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log("[3] thread.isInterrupted()=" + Thread.currentThread().isInterrupted());
            }
        }
    }
}
