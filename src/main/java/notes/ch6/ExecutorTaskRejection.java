package notes.ch6;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 02/09/24
 */
public class ExecutorTaskRejection {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is working really hard.");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " finally work is done.");
            } catch (Exception e) {
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executor.submit(task);
        }

        Thread.sleep(4000);

        List<Runnable> pendingTask = executor.shutdownNow(); // this will kill the already executing task.

        System.out.println("Pending task list :" + pendingTask.size());


    }

}
