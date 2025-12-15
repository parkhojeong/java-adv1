package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV3 implements BoundedQueue{
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] queue is full, wait..");
            try {
                wait(); // RUNNABLE -> WAITING, LOCK RETURN
                log("[put] wake up");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] notify call");
        notify(); // THREAD WAITING SET(WAITING -> BLOCKED(WAIT LOCK) -> RUNNABLE)
    }

    @Override
    public synchronized String take() {
        while(queue.isEmpty()){
            log("[take] queue is empty, wait..");
            try {
                wait();
                log("[take] wake up");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        String data = queue.poll();
        log("[take] notify call");
        notify();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
