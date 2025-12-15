package thread.bounded;

import static util.MyLogger.log;

public class ProducerTask implements Runnable{
    private BoundedQueue queue;
    private String request;

    public ProducerTask(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[producer start] " + request + ", queue=" + queue);
        queue.put(request);
        log("[producer completed] " + request + ", queue=" + queue);

    }
}
