package notes.ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 19/08/24
 */

public class ThreadLocks {

    public static int SUM = 0;
    public static Lock lock = new ReentrantLock();
    private static int[] arr = new int[10];

    public static void main(String[] args) {

        for (int i = 0; i < arr.length; i++) arr[i] = 10;

        System.out.println(Arrays.toString(arr));

        int threadSlice = arr.length / 2;

        List<Thread> listOfThread = new ArrayList<>();

        for (int count = 0; count < 2; count++) {
            Thread t = new Thread(new AggregateWorker(count * threadSlice, (count + 1) * threadSlice));
            t.start();
            listOfThread.add(t);
        }

        listOfThread.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Total sum : " + SUM);


    }

    static class AggregateWorker implements Runnable {

        private int left;
        private int right;

        public AggregateWorker(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                lock.lock();
                SUM = SUM + arr[i];
                lock.unlock();
            }
        }
    }

}
