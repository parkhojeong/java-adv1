package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {

//        test(new BasicList());
        BasicList target = new BasicList();
        SyncProxyList syncProxyList = new SyncProxyList(target);
        test(syncProxyList);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("t1: list.add(A)");
            }
        };
        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("t1: list.add(B)");
            }
        };

        Thread t1 = new Thread(addA, "t1");
        Thread t2 = new Thread(addB, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log(list);
    }
}
