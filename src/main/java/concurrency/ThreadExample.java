public class ThreadExample {
    public static void main(String[] args) {
        // Create a thread using a lambda expression
        Thread thread1 = new Thread(() -> System.out.println("Thread 1 is running"));

        // Create another thread using a lambda expression
        Thread thread2 = new Thread(() -> System.out.println("Thread 2 is running"));

        // Start the threads
        thread1.start();
        thread2.start();
    }
}
