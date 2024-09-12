package bhargrah.ms.pattern;

/**
 * Key Concepts
 * Lazy Initialization:
 * The Singleton instance is created only when it's first accessed through the getInstance() method. This is because the SingletonHelper class is not loaded until it is explicitly referenced.
 * <p>
 * Thread Safety:
 * The class loader mechanism in Java guarantees that a class is only loaded once. Since the SingletonHelper class contains the INSTANCE variable and it is a static final field, its initialization is thread-safe without requiring explicit synchronization.
 * <p>
 * No Synchronization Overhead:
 * Unlike other thread-safe implementations that use synchronized blocks, the Bill Pugh approach does not involve any synchronization, making it more efficient.
 * <p>
 * Class Loading:
 * In Java, a class is loaded into memory by the class loader when it is first used.
 * The inner static class SingletonHelper is loaded only when getInstance() is called
 * for the first time, ensuring that the singleton instance is created lazily.
 * <p>
 * Why This Approach Is Preferred
 * <p>
 * Efficiency: It avoids the synchronization overhead, which can be significant in highly concurrent applications.
 * <p>
 * Thread Safety: It guarantees that the singleton instance is created only once, in a thread-safe manner, without the need for complex synchronization.
 * <p>
 * Simplicity: The design is straightforward and leverages the Java language's features, making it easy to understand and implement.
 * <p>
 * When to Use It
 * The Bill Pugh Singleton Design is ideal for scenarios where:
 * <p>
 * You need to ensure that a class has only one instance.
 * The instance should be created lazily, only when it is needed.
 * You require a solution that is both thread-safe and performant.
 * This approach is considered best practice in most situations requiring a Singleton in Java due to its balance between simplicity, performance, and thread safety.
 */
public class Singleton {

    private Singleton() {
        // private constructor to prevent instantiation
    }

    private static class SingletonHelper {

        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}