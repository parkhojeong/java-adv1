package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureExceptionMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("작업 전달");
        Future<Integer> future = es.submit(new MyCallable());
        sleep(1000);

        try {
            log("future.get(), future.state() = " + future.state());
            Integer result = future.get();
            log("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log("e = " + e);
            log("e.getCause() = " + e.getCause());

        }

        es.close();
    }

    private static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("Callable start, exception throw");
            throw new IllegalArgumentException("exception");
        }
    }
}
