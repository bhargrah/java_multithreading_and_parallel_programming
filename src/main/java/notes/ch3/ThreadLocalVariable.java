package notes.ch3;

// TODO  : Work on the same

import java.util.Map;
import java.util.HashMap;

public class ThreadLocalVariable {

    // Define a ThreadLocal variable to hold the cookies per thread
    private static final ThreadLocal<Map<String, String>> threadLocalCookies = ThreadLocal.withInitial(HashMap::new);

    // Method to set a cookie for the current thread
    public static void setCookie(String name, String value) {
        Map<String, String> cookies = threadLocalCookies.get();
        cookies.put(name, value);
        threadLocalCookies.set(cookies);
    }

    // Method to get a cookie by name for the current thread
    public static String getCookie(String name) {
        Map<String, String> cookies = threadLocalCookies.get();
        return cookies.get(name);
    }

    // Method to clear all cookies for the current thread
    public static void clearCookies() {
        threadLocalCookies.get().clear();
    }

    public static void main(String[] args) {
        // Simulating multiple threads using the CookieManager
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();

            // Set some cookies for the current thread
            setCookie(threadName + "-SessionID", threadName + "-12345");
            setCookie(threadName + "-UserID", threadName + "-User");

            // Get and print the cookies for the current thread
            System.out.println(threadName + " - SessionID: " + getCookie(threadName + "-SessionID"));
            System.out.println(threadName + " - UserID: " + getCookie(threadName + "-UserID"));

            // Clear cookies
            clearCookies();
        };

        // Start multiple threads to demonstrate ThreadLocal usage
        Thread thread1 = new Thread(task, "Thread1");
        Thread thread2 = new Thread(task, "Thread2");

        thread1.start();
        thread2.start();
    }

}
