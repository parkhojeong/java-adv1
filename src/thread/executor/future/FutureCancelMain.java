package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {
//    private static boolean mayInterruptIfRunning = true;
    private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state=" + future.state());

        sleep(3000);

        log("Future.cancel(" + mayInterruptIfRunning + ")");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("Future.cancel() return=" + cancelResult);

        log("Future.isDone()=" + future.isDone());

        try {
            log("Futere.get()=" + future.get());
        } catch (CancellationException e) {
            log("Future.get() CancellationException");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call(){
            try {
                for (int i = 0; i < 10; i++) {
                    log("working.." + i);
                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException e) {
                log("interrupted");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}
