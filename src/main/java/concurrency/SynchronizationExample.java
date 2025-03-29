public class SynchronizationExample {
    private int counter = 0;

    // Synchronized method to ensure thread-safe increment
    public synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizationExample example = new SynchronizationExample();

        // Create two threads that increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) example.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) example.increment();
        });

        // Start the threads
        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // Print the final counter value
        System.out.println("Final Counter: " + example.counter); // Should be 2000
    }
}
