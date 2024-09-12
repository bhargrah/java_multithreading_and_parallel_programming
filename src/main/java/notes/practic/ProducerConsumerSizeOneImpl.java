package notes.practic;

import java.util.*;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 29/08/24
 */

class Producer implements Runnable {

    Queue<Integer> queue;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                produce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void produce() throws InterruptedException {
        synchronized (queue) {

            if (queue.size() == 1) queue.wait();
            int data = new Random().nextInt(0, 9);
            queue.add(data);
            System.out.println(Thread.currentThread().getName() + " producing message " + data);

            queue.notifyAll();
        }
    }
}

class Consumer implements Runnable {

    Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void consume() throws InterruptedException {

        synchronized (queue) {

            if (queue.isEmpty()) queue.wait();

            System.out.println(Thread.currentThread().getName() + " consuming message " + queue.remove());
            queue.notifyAll();
        }
    }
}

public class ProducerConsumerSizeOneImpl {

    public static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {

        Thread p = new Thread(new Producer(queue));
        Thread c = new Thread(new Consumer(queue));

        p.start();
        c.start();

    }

}
