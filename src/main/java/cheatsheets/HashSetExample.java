package cheatsheets;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        // Creating a HashSet
        Set<String> set = new HashSet<>();

        // Adding elements
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // Duplicate - won't be added
        System.out.println("After adds: " + set);

        // Checking existence
        boolean hasApple = set.contains("Apple");  // true
        boolean hasOrange = set.contains("Orange");  // false
        System.out.println("Has Apple? " + hasApple);
        System.out.println("Has Orange? " + hasOrange);

        // Safe removal
        boolean removed = set.remove("Banana");
        System.out.println("Banana removed? " + removed);
        System.out.println("After removal: " + set);

        // Bulk operations
        Set<String> otherSet = new HashSet<>();
        otherSet.add("Apple");
        otherSet.add("Orange");

        // Union
        Set<String> union = new HashSet<>(set);
        union.addAll(otherSet);
        System.out.println("Union: " + union);

        // Intersection
        Set<String> intersection = new HashSet<>(set);
        intersection.retainAll(otherSet);
        System.out.println("Intersection: " + intersection);

        // Difference
        Set<String> difference = new HashSet<>(set);
        difference.removeAll(otherSet);
        System.out.println("Difference: " + difference);

        // Modern iteration
        System.out.println("\nIterating set:");
        set.forEach(System.out::println);

        // Size operations
        System.out.println("Size: " + set.size());
        set.clear();
        System.out.println("Is empty? " + set.isEmpty());
    }
}