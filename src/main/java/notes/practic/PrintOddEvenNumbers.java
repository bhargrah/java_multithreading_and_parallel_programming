package notes.practic;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Print number incrementally by odd/even by two threads.
 * @date 31/08/24
 */

class Printer {

    private final Object lock = new Object();

    private int number = 1;

    private int max;

    public Printer(int max) {
        this.max = max;
    }

    public void printEven() {
        synchronized (lock) {
            while (number < max) {
                if (number % 2 != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " printing " + number);
                number++;

                lock.notifyAll();

            }
        }

    }

    public void printOdd() {
        synchronized (lock) {
            while (number < max) {
                if (number % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println(Thread.currentThread().getName() + "  printing " + number);
                number++;

                lock.notifyAll();

            }
        }

    }

}

public class PrintOddEvenNumbers {
 
    public static void main(String[] args) {

        Printer printer = new Printer(10);
        Thread odd = new Thread(printer::printOdd);
        odd.setName("ODD");
        Thread even = new Thread(printer::printEven);
        even.setName("EVEN");

        odd.start();
        even.start();
    }

}
