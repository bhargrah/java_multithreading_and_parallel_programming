package notes.ch3;

import java.util.Random;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */

class OrderProcessingTask implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is processing Order Number : " + new Random().nextInt());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + " interrupted with priority = " + thread.getPriority());
        }
    }
}

public class ThreadGroupMain {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup orderProcessingGroup = new ThreadGroup("Order Processing Group");

        Thread orderProcessor1 = new Thread(orderProcessingGroup, new OrderProcessingTask());
        Thread orderProcessor2 = new Thread(orderProcessingGroup, new OrderProcessingTask());
        Thread orderProcessor3 = new Thread(orderProcessingGroup, new OrderProcessingTask());

        orderProcessor1.start();
        orderProcessor2.start();
        orderProcessor3.start();

        Thread.sleep(3000);

        // this is a good utility if all the threads within the group need to be interrupted
        orderProcessingGroup.interrupt();

        //


    }

}
