package cheatsheets;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Min Heap (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        System.out.println("=== Min Heap Examples ===");
        
        // Adding elements
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        minHeap.offer(1);
        minHeap.offer(3);
        
        System.out.println("Peek (minimum element): " + minHeap.peek());
        System.out.println("Remove and return minimum: " + minHeap.poll());
        System.out.println("After poll, peek: " + minHeap.peek());
        System.out.println("Size: " + minHeap.size());
        
        // Max Heap using custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        System.out.println("\n=== Max Heap Examples ===");
        
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(1);
        maxHeap.offer(3);
        
        System.out.println("Peek (maximum element): " + maxHeap.peek());
        System.out.println("Remove and return maximum: " + maxHeap.poll());
        System.out.println("After poll, peek: " + maxHeap.peek());
        
        // Custom object example
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        System.out.println("\n=== Custom Object Priority Queue ===");
        
        taskQueue.offer(new Task("Low priority", 3));
        taskQueue.offer(new Task("High priority", 1));
        taskQueue.offer(new Task("Medium priority", 2));
        
        while (!taskQueue.isEmpty()) {
            System.out.println("Processing: " + taskQueue.poll());
        }
    }
    
    // Example custom class for PriorityQueue
    static class Task implements Comparable<Task> {
        String description;
        int priority; // Lower number = higher priority
        
        public Task(String description, int priority) {
            this.description = description;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
        
        @Override
        public String toString() {
            return String.format("Task(priority=%d, description='%s')", priority, description);
        }
    }
}
