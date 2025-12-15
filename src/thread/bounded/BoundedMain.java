package thread.bounded;

import java.util.ArrayList;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {
    public static void main(String[] args) {
        BoundedQueue queue = new BoundedQueueV1(2);

        producerFirst(queue);
//        consumerFirst(queue);
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [consumer first], start" + queue.getClass().getSimpleName() + "==");
        ArrayList<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("== [consumer first], end" + queue.getClass().getSimpleName() + "==");
    }

    private static void producerFirst(BoundedQueue queue) {
        log("== [producer first], start" + queue.getClass().getSimpleName() + "==");
        ArrayList<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        log("== [producer first], end" + queue.getClass().getSimpleName() + "==");
    }


    private static void startProducer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("producer start");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("consumer start");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터 + " + queue);
        for (Thread thread: threads){
            log(thread.getName() + ": " + thread.getState());
        }
    }
}
