package cheatsheets;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Creating a HashMap
        Map<String, Integer> map = new HashMap<>();

        // Adding elements
        map.put("A", 1);
        map.put("B", 2);
        // putIfAbsent - only puts if key doesn't exist
        map.putIfAbsent("A", 100);  // won't change A's value
        System.out.println("After puts: " + map);

        // Accessing elements
        // Safe way to get with default value
        int valueA = map.getOrDefault("A", 0);  // returns 1
        int valueC = map.getOrDefault("C", 0);  // returns 0
        System.out.println("Value A: " + valueA);
        System.out.println("Value C (default): " + valueC);

        // Checking existence
        boolean hasKey = map.containsKey("B");  // true
        boolean hasValue = map.containsValue(2);  // true

        // Updating values
        // Compute if present
        map.computeIfPresent("B", (key, val) -> val * 2);
        System.out.println("After compute B: " + map);  // B=4

        // Safe removal
        Integer removed = map.remove("A");
        System.out.println("Removed value: " + removed);

        // Iterating - Modern way
        System.out.println("\nIterating entries:");
        map.forEach((key, value) -> 
            System.out.println(key + " -> " + value));

        // Getting views
        System.out.println("\nKeys: " + map.keySet());
        System.out.println("Values: " + map.values());
        System.out.println("Entries: " + map.entrySet());

        // Clearing and checking size
        System.out.println("Size: " + map.size());
        map.clear();
        System.out.println("Is empty? " + map.isEmpty());
    }
}