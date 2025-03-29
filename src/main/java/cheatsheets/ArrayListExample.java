package cheatsheets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        // Creating ArrayList
        List<String> list = new ArrayList<>();  // Diamond operator

        // Adding elements
        list.add("First");
        list.add("Second");
        list.add("Third");
        System.out.println("After adds: " + list);

        // Adding at specific index
        list.add(1, "Inserted");
        System.out.println("After insert: " + list);

        // Accessing elements
        String element = list.get(0);  // First element
        System.out.println("First element: " + element);

        // Updating elements
        list.set(0, "Updated First");
        System.out.println("After update: " + list);

        // Removing elements
        list.remove("Second");  // Remove by object
        list.remove(0);        // Remove by index
        System.out.println("After removes: " + list);

        // Checking content
        boolean contains = list.contains("Third");
        System.out.println("Contains 'Third'? " + contains);

        // Finding elements
        int index = list.indexOf("Third");
        System.out.println("Index of 'Third': " + index);

        // Bulk operations
        List<String> additional = new ArrayList<>();
        additional.add("Fourth");
        additional.add("Fifth");
        list.addAll(additional);
        System.out.println("After addAll: " + list);

        // Sublist (view of original list)
        List<String> sub = list.subList(0, 2);  // endIndex is exclusive
        System.out.println("Sublist: " + sub);

        // Sorting
        Collections.sort(list);  // Natural ordering
        System.out.println("Sorted: " + list);

        // Modern iteration
        System.out.println("\nForEach iteration:");
        list.forEach(System.out::println);

        // Clearing and checking size
        System.out.println("\nSize: " + list.size());
        list.clear();
        System.out.println("Is empty? " + list.isEmpty());

        // Initial capacity (for performance when size is known)
        List<Integer> numbers = new ArrayList<>(100);  // Initial capacity 100

        // Converting to array
        String[] array = list.toArray(new String[0]);  // Preferred way
    }
}