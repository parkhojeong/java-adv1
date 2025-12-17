package thread.executor.test;

public class OldOrderServiceTestMain {
    public static void main(String[] args) {
        String orderNo = "123456";
        OldOrderService service = new OldOrderService();
        service.order(orderNo);
    }
}
