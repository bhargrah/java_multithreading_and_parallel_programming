package notes.ch3;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */


class MyThread extends Thread {

    public void run() {
        System.out.println("Current thread:" + Thread.currentThread().getName());
    }
}

public class ThreadCreate {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println("Current thread:" + thread);

        //Thread.sleep(3000);

        // Create a thread
        //1. Extends the thread call
        //2. Use runnable interfaces

        MyThread thread1 = new MyThread();
        thread1.start();

        Runnable runnable = () -> System.out.println("Runnable Task");
        Thread thread_1 = new Thread(runnable);
        thread_1.start();

        thread_1.join();
        System.out.println("Current thread:" + thread);


    }


}
