package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {
    public static void main(String[] args) throws InterruptedException {
        Job task1 = new Job(1, 50);
        Job task2 = new Job(51, 100);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();

        thread1.join(500);
        thread2.join(500);
        int sum = task1.result + task2.result;
        log(sum);

    }

    static class Job implements Runnable {
        int start;
        int end;
        int result;

        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getResult() {
            return result;
        }

        @Override
        public void run() {
            sleep(2000);
            for (int i = start; i <= end; i++) {
                result += i;
            }
            log(Thread.currentThread().getName() + " end");
        }
    }
}
