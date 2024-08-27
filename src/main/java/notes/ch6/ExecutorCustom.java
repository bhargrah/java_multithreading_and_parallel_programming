package notes.ch6;

import java.util.concurrent.*;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 25/08/24
 */
public class ExecutorCustom {

    public static void main(String[] args) {

        // ThreadPoolExecutor is the base class which is used to create different strategies
        ExecutorService pool = new ThreadPoolExecutor(
                2,
                4,
                20,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                (r, executor) -> System.out.println("Too many task , rejecting tasks")   // RejectedExecutionHandler instance
        );

        pool.submit(() -> System.out.println("Task 1"));
        pool.submit(() -> System.out.println("Task 2"));
        pool.submit(() -> System.out.println("Task 3"));
        pool.submit(() -> System.out.println("Task 4"));

        pool.shutdown();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() -> System.out.println("Task 2"));
        executor.submit(() -> System.out.println("Task 3"));
        executor.submit(() -> System.out.println("Task 4"));

        executor.shutdown();

    }

}
