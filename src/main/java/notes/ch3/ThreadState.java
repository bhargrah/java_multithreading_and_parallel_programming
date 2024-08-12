package notes.ch3;

/**
 * @author Rahul Kumar Bhargava
 * @version 1.0
 * @description Description of the purpose of this file.
 * @date 11/08/24
 */
public class ThreadState {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> System.out.println("[1] State : " + Thread.currentThread().getState()));

        System.out.println("[2] State : " + thread.getState());

        thread.start();

        System.out.println("[3] State : " + thread.getState());

        thread.join();

        System.out.println("[4] State : " + thread.getState());


    }

}
