package thread.executor.future;

import thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.MyLogger.log;

public class InvokeAnyMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask t1 = new CallableTask("t1", 1000);
        CallableTask t2 = new CallableTask("t2", 2000);
        CallableTask t3 = new CallableTask("t3", 3000);

        Integer value = es.invokeAny(List.of(t1, t2, t3));
        log("value = " + value);

        es.close();
    }
}
