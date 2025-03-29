import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceExample {
    public static void main(String[] args) {
        // Create an object and a reference queue
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();

        // Create a phantom reference to the object
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, refQueue);

        // Print the phantom reference before garbage collection
        System.out.println("Before GC: " + phantomRef.get()); // Always null

        // Remove the strong reference and trigger garbage collection
        obj = null;
        System.gc();

        // Check if the phantom reference is enqueued
        System.out.println("Is phantom reference enqueued? " + (refQueue.poll() != null));
    }
}
