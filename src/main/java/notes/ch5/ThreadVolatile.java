package notes.ch5;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Write logic to guarantee shut down of thread task.
 * @date 24/08/24
 */

class Reader {

    // private volatile boolean isLive = true;  --> Solution
    private boolean isLive = true;

    public void shutdown() {
        System.out.println(Thread.currentThread().getName() + " initiated shutdown (flag = false)");
        isLive = false;
    }

    public void read() {
        System.out.println(Thread.currentThread().getName() + " started the read task ");
        while (isLive) {
            // doing something very meaningful :)
        }
        System.out.println(Thread.currentThread().getName() + " completed the read task ");


    }
}

public class ThreadVolatile {


    public static void main(String[] args) throws InterruptedException {

        Reader reader = new Reader();
        Thread r1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reader.shutdown();
        });

        Thread r2 = new Thread(() -> reader.read());

        r2.start();
        r1.start();

    }

}
