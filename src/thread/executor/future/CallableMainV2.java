package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("submit() called");
        Future<Integer> future = es.submit(new MyCallable());
        log("future = " + future);

        log("get() called. main thread WAITING");
        Integer result = future.get();
        log("get() returned. main thread RUNNABLE" );

        log("result value = " + result);
        log("future = " + future);
        es.close();
    }

    static class MyCallable implements Callable<Integer>{
        @Override
        public Integer call() {
            log("Callable start");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("created value = " + value);
            log("Callable end");
            return value;
        }
    }
}
