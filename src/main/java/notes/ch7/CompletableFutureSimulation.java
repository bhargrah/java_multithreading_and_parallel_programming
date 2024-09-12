package notes.ch7;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Implement an actual use case for completable future.
 * @date 04/09/24
 */

record Order(Integer orderId, Integer price, String status) {

}

class OrderProcessor {

    // Create order
    // Price order
    // Dispatch order
    // Send order details
    public Order createOrder() {
        System.out.println(Thread.currentThread().getName() + " accepted inquiry, creating order.");
        sleep(2000);
        int order = new Random().nextInt(6, 20);
        System.out.println(Thread.currentThread().getName() + " created order with.");
        return new Order(order, 0, "CREATED");
    }

    public Order calculatePriceForOrder(Order order) {
        System.out.println(Thread.currentThread().getName() + " accepted order, calculating price.");
        sleep(2000);
        int price = new Random().nextInt(100, 4000);
        System.out.println(Thread.currentThread().getName() + " pricing done for order : " + order.orderId() + " at " + price);
        return new Order(order.orderId(), price, "PRICED");
    }

    public Order preparingOrderForDispatch(Order order) {
        System.out.println(Thread.currentThread().getName() + " accepted order, preparing dispatch.");
        sleep(3000);
        System.out.println(Thread.currentThread().getName() + " dispatching order : " + order.orderId());
        return new Order(order.orderId(), order.price(), "DISPATCHED");
    }

    public void sendOrderDetails(Order order) {
        System.out.println(Thread.currentThread().getName() + " accepted processed, notification triggered via email.");
    }

    private static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void initiateOrderProcessing() {

        CompletableFuture.supplyAsync(() -> createOrder())
                .thenApply(order -> calculatePriceForOrder(order))
                .thenApply(order -> preparingOrderForDispatch(order))
                .thenAccept(order -> sendOrderDetails(order))
                .join();

    }

}

public class CompletableFutureSimulation {

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> processor.initiateOrderProcessing());
        executor.execute(() -> processor.initiateOrderProcessing());
        executor.execute(() -> processor.initiateOrderProcessing());
        executor.execute(() -> processor.initiateOrderProcessing());

    }
}
