package bhargrah.ms.problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestartMachine {

    public static void main(String[] args) {
        new RestartMachine().runCrashSimulation();
    }

    private void runCrashSimulation() {
        Runnable crash = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Starting on thread: " + name);
            try {
                Thread.sleep((long) (5000 * Math.random()));
            } catch (InterruptedException e) {
                System.out.println("Interrupted on " + name);
            }
            System.out.println("Finished on " + name);
        };

        ExecutorService cached = Executors.newCachedThreadPool();

        for (int i = 0; i < 7_000; i++) { // this simulation should crash machine or force restart
            cached.submit(crash);
        }

        while (!cached.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cached.shutdown();

    }
}