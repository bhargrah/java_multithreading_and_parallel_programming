package notes.ch3;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Wayus to stop the threads (ask politely).
 * @date 03/09/24
 */
class KillableTask implements Runnable {

    public volatile boolean isKilled = false;

    @Override
    public void run() {
        while (!isKilled) {
            System.out.println(Thread.currentThread().getName() + " performing super important task");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// TODO :: Implement this via AtomicBoolean
// TODO :: Implement the same via Interrupts

public class ThreadStop {

    public static void main(String[] args) throws InterruptedException {
        KillableTask task = new KillableTask();
        Thread t = new Thread(task);
        t.start();
        Thread.sleep(5000);
        task.isKilled = true;
    }

}

// TODO :: Try with Future --> cancel feature & timeout feature
// TODO :: Scheduler Service to call stop task say after 10 min
