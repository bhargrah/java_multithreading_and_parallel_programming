package notes.ch6;

import java.util.concurrent.*;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 25/08/24
 */

class IOTask implements Runnable {

    private final int id;

    public IOTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " initiated I/O task with id : " + id);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ExecutorExceptionHandling {

    public static void main(String[] args) {
        executorWithoutHandler();

        ExecutorService poolEx = new ThreadPoolExecutor(
                2,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        new Thread(r).start();
                    }
                }
        );

        poolEx.submit(new IOTask(1));
        poolEx.submit(new IOTask(2));
        poolEx.submit(new IOTask(3));
        poolEx.submit(new IOTask(4));
        poolEx.submit(new IOTask(5));  // this task will be executed by Thread-0

        poolEx.shutdown();

    }

    private static void executorWithoutHandler() {
        ExecutorService pool = null;
        try {
            pool = new ThreadPoolExecutor(
                    2,
                    2,
                    10,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(2)
                    //(r, executor) -> System.out.println("Too many task , rejecting tasks")   // RejectedExecutionHandler instance
            );

            pool.submit(new IOTask(1));
            pool.submit(new IOTask(2));
            pool.submit(new IOTask(3));
            pool.submit(new IOTask(4));
            pool.submit(new IOTask(5));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

}
