package notes.ch4;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Implement producer/consumer problem using condition and lock APIs
 * - Check condition when we have queue size asn
 * @date 19/08/24
 */
public class ThreadLockCondition {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static Queue<Integer> workQueue = new LinkedList<>();


    public static void main(String[] args) {

        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        producer.start();
        consumer.start();

    }

    static class Producer implements Runnable {

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
            lock.lock();

            //if (workQueue.size() == 10)
            if (workQueue.size() == 1)
                condition.await();

            Thread.sleep(1000);

            int order = new Random().nextInt();
            System.out.println("Producer adding order : " + order);
            workQueue.add(order);

            //if (workQueue.isEmpty())
            condition.signal();

            lock.unlock();
        }
    }

    static class Consumer implements Runnable {

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
            lock.lock();

            if (workQueue.isEmpty())
                condition.await();


            System.out.println("Consumer processing order : " + workQueue.remove());

            Thread.sleep(1000);

            //if (workQueue.size() == 1)
            condition.signal();

            lock.unlock();

        }
    }


}
