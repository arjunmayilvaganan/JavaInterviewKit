package cheatsheets;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;

public class QueueExample {
    public static void main(String[] args) {
        // LinkedList implementation of Queue
        System.out.println("=== Queue (LinkedList) Examples ===");
        Queue<String> queue = new LinkedList<>();

        // Adding elements
        queue.offer("first");     // Preferred over add() - returns false if full
        queue.offer("second");
        System.out.println("After offers: " + queue);  // [first, second]

        // Examining elements
        String front = queue.peek();  // Preferred over element() - returns null if empty
        System.out.println("Front element (peek): " + front);  // first

        // Removing elements
        String removed = queue.poll();  // Preferred over remove() - returns null if empty
        System.out.println("Removed element: " + removed);  // first
        System.out.println("After poll: " + queue);  // [second]

        // Other useful methods
        System.out.println("Size: " + queue.size());
        System.out.println("Is empty? " + queue.isEmpty());
        queue.clear();  // Remove all elements

        // ArrayDeque as a more efficient Queue
        System.out.println("\n=== ArrayDeque as Queue Examples ===");
        Queue<Integer> arrayDeque = new ArrayDeque<>();  // Generally better performance
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);
        System.out.println("ArrayDeque: " + arrayDeque);

        // Safe way to process queue
        while (!arrayDeque.isEmpty()) {
            System.out.println("Processing: " + arrayDeque.poll());
        }
    }
}