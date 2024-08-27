package notes.ch5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Write class for shared counter.
 * @date 24/08/24
 */

class Counter {

    private Integer counter = 0;

    public int getCount() {
        return counter;
    }

    public synchronized void increment() {
        counter = counter + 1;
    }

    public synchronized void decrement() {
        counter = counter - 1;
    }

}

class AtomicCounter {

    private AtomicInteger counter = new AtomicInteger(0);

    public int getCount() {
        return counter.get();
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public void decrement() {
        counter.decrementAndGet();
    }

}

public class ThreadAtomicCounter {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        //AtomicCounter counter = new AtomicCounter();

        Thread increment = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread decrement = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        });

        Thread random = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                counter.decrement();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 300; i++) {
                counter.increment();
            }
        });

        increment.start();
        decrement.start();
        random.start();

        increment.join();
        decrement.join();
        random.join();

        System.out.println("Counter value is : " + counter.getCount());
    }

}
