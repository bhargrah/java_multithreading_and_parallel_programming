package notes.ch4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Write producer consumer where exchange happens via queue with size 1
 * @date 12/08/24
 */
public class ThreadSynchronization {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

    }

    static class Consumer implements Runnable {

        Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        private void produce() throws InterruptedException {

            synchronized (queue) {
                if (queue.size() == 1) {
                    //System.out.println("Producer is waiting now ....");
                    queue.wait();
                }

                int num = new Random().nextInt();
                queue.add(num);
                System.out.println("Message added to the queue by producer : " + num);

                Thread.sleep(1000);

                //if (queue.size() == 0)
                queue.notifyAll();

            }
        }
    }

    static class Producer implements Runnable {

        Queue<Integer> queue;

        public Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        private void consume() throws InterruptedException {

            synchronized (queue) {
                if (queue.size() == 0) {
                    //System.out.println("Consumer is waiting now ....");
                    queue.wait();
                }

                Thread.sleep(1000);
                System.out.println("Message processed by consumer : " + queue.remove());

                //if (queue.size() == 1)
                queue.notifyAll();


            }

        }
    }


}
