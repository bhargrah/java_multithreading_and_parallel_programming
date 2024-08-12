package notes.ch3;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */
public class ThreadPriority {

    public static void main(String[] args) throws InterruptedException {

        Thread threadMax = new Thread(() -> System.out.println(Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority()));
        threadMax.setName("Max Thread");
        Thread threadMin = new Thread(() -> System.out.println(Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority()));
        threadMin.setName("Min Thread");

        // Thread scheduler checks and picks up threads with high priority.
        threadMax.setPriority(Thread.MAX_PRIORITY); // priorities [0 - 10]
        threadMin.setPriority(Thread.MIN_PRIORITY); // priorities [0 - 10]

        threadMin.start();
        threadMax.start();

        threadMax.join();
        threadMin.join();

    }

}
