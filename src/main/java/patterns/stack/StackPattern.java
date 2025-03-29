package patterns.stack;

import java.util.*;

public class StackPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Valid Parentheses
     * 2. Min Stack
     * 3. Daily Temperatures
     * 4. Largest Rectangle in Histogram
     */
    
    // Example 1: Valid Parentheses (LeetCode 20)
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');
        
        for (char c : s.toCharArray()) {
            if (!bracketMap.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || stack.pop() != bracketMap.get(c)) {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    // Example 2: Min Stack (LeetCode 155)
    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;
        
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    // Example 3: Daily Temperatures (LeetCode 739)
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                result[prevDay] = i - prevDay;
            }
            stack.push(i);
        }
        
        return result;
    }
    
    // Example 4: Largest Rectangle in Histogram (LeetCode 84)
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        // Test Valid Parentheses
        String s = "()[]{}";
        System.out.println("Is valid parentheses: " + isValid(s));
        
        // Test MinStack
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Minimum element: " + minStack.getMin());
        minStack.pop();
        System.out.println("Top element: " + minStack.top());
        System.out.println("New minimum: " + minStack.getMin());
        
        // Test Daily Temperatures
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Days until warmer: " + 
                         Arrays.toString(dailyTemperatures(temperatures)));
        
        // Test Largest Rectangle in Histogram
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest rectangle area: " + 
                         largestRectangleArea(heights));
    }
}