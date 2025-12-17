package thread.executor.test;

public class NewOrderServiceTestMain {
    public static void main(String[] args) {
        String orderNo = "123456";
        NewOrderService newOrderService = new NewOrderService();
        newOrderService.order(orderNo);
    }
}
