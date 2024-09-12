package notes.practic;

import java.util.*;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 31/08/24
 */

class Writer implements Runnable {

    Queue<Integer> queue;

    public Writer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                write();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void write() throws InterruptedException {

        synchronized (queue) {

            if (queue.size() == 10) queue.wait();

            int data = new Random().nextInt(1, 8);
            System.out.println(Thread.currentThread().getName() + " adding data " + data);
            queue.add(data);

            if (queue.size() == 1) queue.notifyAll();
        }

    }
}

class Reader implements Runnable {

    Queue<Integer> queue;

    public Reader(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                read();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void read() throws InterruptedException {
        synchronized (queue) {

            if (queue.isEmpty()) queue.wait();
            System.out.println(Thread.currentThread().getName() + " consuming data " + queue.remove());

            if (queue.size() == 10 - 1)
                queue.notifyAll();
        }
    }
}


public class ProducerConsumerWithFixedSize {

    public static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        Thread p = new Thread(new Writer(queue));
        p.setName("Producer");
        Thread c = new Thread(new Reader(queue));
        c.setName("Consumer");

        p.start();
        c.start();
    }

}
