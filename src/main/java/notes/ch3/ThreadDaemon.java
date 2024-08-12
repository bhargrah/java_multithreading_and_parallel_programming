package notes.ch3;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */

class SlowTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread + " starting execution");
            Thread.sleep(2000);
            System.out.println(currentThread + " finished execution");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ThreadDaemon {

    public static void main(String[] args) throws InterruptedException {

        Thread daemon = new Thread();
        daemon.setDaemon(true);

        Thread t2 = new Thread(new SlowTask());
        Thread t3 = new Thread(new SlowTask());

        daemon.start();
        t2.start();
        t3.start();

        daemon.join();
    }

}
