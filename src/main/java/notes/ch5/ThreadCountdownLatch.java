package notes.ch5;

import java.util.concurrent.CountDownLatch;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Search a given number in an array via 2 threads.
 * @date 20/08/24
 */
public class ThreadCountdownLatch {

    static int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    static boolean numberFound = false;

    static int needle = 9;

    static CountDownLatch latch = new CountDownLatch(2);


    public static void main(String[] args) throws InterruptedException {

        int len = arr.length;

        Thread t1 = new Thread(new NumberSearcher(0, len / 2));
        Thread t2 = new Thread(new NumberSearcher(len / 2 + 1, len - 1));

        t1.start();
        t2.start();

        latch.await();

        System.out.println("Number with values " + needle + " is present : " + numberFound);
    }

    static class NumberSearcher implements Runnable {

        private int start;
        private int end;

        public NumberSearcher(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (arr[i] == needle)
                    numberFound = true;
            }

            latch.countDown();

        }
    }


}
