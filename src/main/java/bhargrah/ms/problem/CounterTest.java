package bhargrah.ms.problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Concurrency:
 * <p>
 * In a multithreaded environment, many threads may try to increment the counter
 * simultaneously. The synchronized method ensures that the counter increments
 * correctly, even when accessed by multiple threads at the same time.
 * <p>
 * The volatile keyword ensures that when one thread updates the counter,
 * the new value is immediately visible to all other threads, preventing stale
 * data issues.
 */
class Counter {

    /**
     * Volatile Keyword:
     * <p>
     * The volatile keyword is used to ensure that changes to the count variable
     * are visible to all threads. Normally, without volatile, a thread might cache
     * the value of the variable in its local memory and not see changes made by other threads.
     * <p>
     * By declaring count as volatile, you ensure that every read of count will always return the latest
     * value written to it, ensuring visibility across threads.
     */
    private volatile int count = 0;

    /**
     * Synchronized Keyword:
     * <p>
     * The increment() method is marked as synchronized. This ensures that only one
     * thread can execute this method at a time.
     * <p>
     * Without synchronized, two threads could potentially read the same value of
     * count, increment it, and then both write the same new value, leading to lost
     * updates (race condition).
     * <p>
     * By using synchronized, we ensure atomicity—meaning the read-modify-write sequence
     * in the increment() method happens as a single, indivisible operation.
     */
    public synchronized void increment() {
        count++;
    }

    // Method to get the current value of the counter
    public int getValue() {
        return count;
    }
}

/**
 * Atomic Operations:
 * <p>
 * AtomicInteger provides methods like incrementAndGet(), decrementAndGet(), getAndIncrement(),
 * and others that perform atomic operations. These operations are thread-safe and do not
 * require explicit synchronization.
 * <p>
 * No Need for synchronized:
 * <p>
 * Since AtomicInteger handles the atomicity of the operations internally, there’s no need
 * to use the synchronized keyword or volatile keyword. The AtomicInteger class ensures
 * that all threads see the most up-to-date value, and operations like incrementing are
 * done in a thread-safe manner.
 */
class AtomicCounter {

    // AtomicInteger to handle the counter in a thread-safe manner
    private AtomicInteger count = new AtomicInteger(0);

    // Method to increment the counter
    public void increment() {
        count.incrementAndGet();
    }

    // Method to get the current value of the counter
    public int getValue() {
        return count.get();
    }
}

public class CounterTest {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        AtomicCounter atomicCounter = new AtomicCounter();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Simulate multiple threads incrementing the counter
        for (int i = 0; i < 10_000; i++) executorService.execute(() -> counter.increment());

        for (int i = 0; i < 10_000; i++) executorService.execute(() -> atomicCounter.increment());

        // Shutdown the executor service and wait for tasks to complete
        executorService.shutdown();
        while (!executorService.isTerminated()) { // Wait for all tasks to finish
        }

        // Display the final counter value
        System.out.println("Final Counter Value: " + counter.getValue());
        System.out.println("Final AtomicCounter Value: " + counter.getValue());

    }

}
