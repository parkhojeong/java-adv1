package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable{
    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[consumer start] queue=" + queue);
        String data = queue.take();
        log("[consumer completed] queue=" + queue + ", data=" + data);
    }
}
