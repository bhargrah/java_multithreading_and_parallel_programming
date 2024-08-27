package notes.ch6;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Write program to double each number in an array.
 * @date 26/08/24
 */
public class ForkJoin {

    public static int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoin = new ForkJoinPool(2);

        Future result = forkJoin.submit(new IncrementTask(numbers, 0, numbers.length - 1));

        result.get();

        System.out.println(Arrays.toString(numbers));


    }

    static class IncrementTask extends RecursiveAction {

        final int[] array;
        final int lo, hi;

        IncrementTask(int[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        protected void compute() {
            if (hi - lo < 2) {
                for (int i = lo; i < hi; i++) array[i]++;
            } else {
                int mid = (lo + hi) / 2;
                invokeAll(new IncrementTask(array, lo, mid), new IncrementTask(array, mid, hi));
            }
        }
    }
}

