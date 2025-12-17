package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SumTakMainV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Job job1 = new Job(1, 50);
        Job job2 = new Job(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(job1);
        Future<Integer> future2 = es.submit(job2);

        Integer sum1 = future1.get();
        Integer sum2 = future2.get();

        log("sum1 = " + sum1);
        log("sum2 = " + sum2);

        int sum = sum1 + sum2;
        log("sum = " + sum);
        es.close();

    }

    static class Job implements Callable<Integer> {
        int start;
        int end;

        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() throws InterruptedException {
            log(Thread.currentThread().getName() + " start");
            int result = 0;
            Thread.sleep(2000);
            for (int i = start; i <= end; i++) {
                result += i;
            }
            log(Thread.currentThread().getName() + " end");
            return result;
        }
    }
}
