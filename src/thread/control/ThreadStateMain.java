package thread.control;

import java.util.TreeMap;

import static util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log(thread.getState());
        thread.start();
        Thread.sleep(1000);
        log(thread.getState());
        Thread.sleep(4000);
        log(thread.getState());
        log("end");
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {

            try {
                log("start");
                log(Thread.currentThread().getState());
                log("sleep start");
                Thread.sleep(3000);
                log(Thread.currentThread().getState());
                log("sleep end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
