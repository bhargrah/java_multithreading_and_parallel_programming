package oreilly.execution;

public class SchedulerOverhead {

    public static void main(String[] args) throws InterruptedException {
        SchedulerOverhead so = new SchedulerOverhead();
        so.run();
    }

    private void run() throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000; i++) {
            Thread.sleep(1);
        }
        long end = System.currentTimeMillis();
        System.out.println("Millis elapsed: " + (end - start));
    }
}
