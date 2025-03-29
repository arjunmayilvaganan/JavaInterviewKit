import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        // Create an object and a weak reference to it
        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(obj);

        // Print the object before garbage collection
        System.out.println("Before GC: " + weakRef.get());

        // Remove the strong reference and trigger garbage collection
        obj = null;
        System.gc();

        // Print the object after garbage collection
        System.out.println("After GC: " + weakRef.get()); // Should be null if GC has run
    }
}
