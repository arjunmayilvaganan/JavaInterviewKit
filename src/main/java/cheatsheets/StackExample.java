package cheatsheets;

import java.util.Stack;
import java.util.ArrayDeque;  // Preferred over Stack class

public class StackExample {
    public static void main(String[] args) {
        // Modern way using ArrayDeque (preferred)
        System.out.println("=== ArrayDeque as Stack (Modern) ===");
        ArrayDeque<String> stack = new ArrayDeque<>();

        // Adding elements
        stack.push("first");
        stack.push("second");
        stack.push("third");
        System.out.println("After pushes: " + stack);  // [third, second, first]

        // Examining top element
        String top = stack.peek();
        System.out.println("Top element: " + top);  // third

        // Removing elements
        String popped = stack.pop();
        System.out.println("Popped element: " + popped);  // third
        System.out.println("After pop: " + stack);  // [second, first]

        // Safe way to process stack
        System.out.println("Processing stack:");
        while (!stack.isEmpty()) {
            System.out.println("Processing: " + stack.pop());
        }

        // Legacy Stack class (shown for completeness)
        System.out.println("\n=== Legacy Stack Class ===");
        Stack<Integer> legacyStack = new Stack<>();
        legacyStack.push(1);
        legacyStack.push(2);
        System.out.println("Legacy stack: " + legacyStack);
        System.out.println("Legacy stack pop: " + legacyStack.pop());

        // Note: Prefer ArrayDeque over Stack class as Stack is synchronized 
        // and less efficient for single-threaded use
    }
}