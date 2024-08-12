package notes.ch3;

import java.util.Random;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */

class ExceptionWorker implements Runnable {

    @Override
    public void run() {
        int orderId = new Random().nextInt();
        try {
            System.out.println(Thread.currentThread().getName() + " is processing Order Number : " + orderId);
            Thread.sleep(3000);
            int result = (4 / 0); // trigger exception
        } catch (Exception e) {
            throw new RuntimeException("Error processing orderId : " + orderId + " can't find order in repository");
        }
    }
}

public class ThreadExceptionHandling {

    public static void main(String[] args) {

        Thread orderProcessor = new Thread(new ExceptionWorker());

        // this will typically catch the exception by main thread
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable th) -> {
            System.out.println(th.getMessage());
        });

        // there are others way where threadgroup can also be used to catch and handle exception thrown by thread

        orderProcessor.start();

    }

}
