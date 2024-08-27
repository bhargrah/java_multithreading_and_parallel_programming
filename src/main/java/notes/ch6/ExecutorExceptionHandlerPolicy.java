package notes.ch6;

import java.util.concurrent.*;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 25/08/24
 */
public class ExecutorExceptionHandlerPolicy {

    public static void main(String[] args) {

        int noOfTask = 10;
        IOTask[] ioTasks = new IOTask[noOfTask];

        for (int i = 0; i < ioTasks.length; i++) {
            ioTasks[i] = new IOTask(i);
        }

        ExecutorService poolEx = new ThreadPoolExecutor(
                1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                //new ThreadPoolExecutor.CallerRunsPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );


        // new ThreadPoolExecutor.CallerRunsPolicy() --> this will make main thread run the failed task
        // new ThreadPoolExecutor.AbortPolicy() -->this will reject the task
        try {
            for (int i = 0; i < ioTasks.length; i++) {
                poolEx.submit(ioTasks[i]);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            poolEx.shutdown();
        }


    }

}
