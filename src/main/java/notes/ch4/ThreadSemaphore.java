package notes.ch4;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Implement thread pool (database) via semaphore
 * @problem Common resource needs to be protected by multiple thread s
 * @date 19/08/24
 */

class DatabasePool implements Runnable {

    private Semaphore semaphore;

    public DatabasePool(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            connectToDatabase();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void connectToDatabase() throws InterruptedException {
        semaphore.acquire(2); // acquire permits
        Integer result = new Random().nextInt(3, 20);
        System.out.println(Thread.currentThread().getName() + " performing database updates for " + result + " rows");
        Thread.sleep(2000);
        semaphore.release(2); // release permits
        //semaphore.availablePermits();
    }

}

public class ThreadSemaphore {

    public static Semaphore semaphore = new Semaphore(4);

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DatabasePool(semaphore));
            threads[i].start();
        }

        Arrays.stream(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
