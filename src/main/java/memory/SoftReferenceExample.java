import java.lang.ref.SoftReference;

public class SoftReferenceExample {
    public static void main(String[] args) {
        // Create an object and a soft reference to it
        Object obj = new Object();
        SoftReference<Object> softRef = new SoftReference<>(obj);

        // Print the object before garbage collection
        System.out.println("Before GC: " + softRef.get());

        // Remove the strong reference
        obj = null;

        // Trigger garbage collection
        System.gc();

        // Print the object after garbage collection
        System.out.println("After GC: " + softRef.get()); // May still be available if memory is sufficient
    }
}
