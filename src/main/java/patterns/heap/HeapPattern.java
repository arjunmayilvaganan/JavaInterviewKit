package patterns.heap;

import java.util.*;

public class HeapPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Kth Largest Element
     * 2. Last Stone Weight
     * 3. K Closest Points to Origin
     * 4. Find Median from Data Stream
     */
    
    // Example 1: Kth Largest Element in an Array (LeetCode 215)
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }
    
    // Example 2: Last Stone Weight (LeetCode 1046)
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            
            if (stone1 != stone2) {
                maxHeap.offer(stone1 - stone2);
            }
        }
        
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
    
    // Example 3: K Closest Points to Origin (LeetCode 973)
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> 
            (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        int[][] result = new int[k][2];
        while (k > 0) {
            result[--k] = maxHeap.poll();
        }
        
        return result;
    }
    
    // Example 4: Find Median from Data Stream (LeetCode 295)
    static class MedianFinder {
        private PriorityQueue<Integer> small;  // max heap
        private PriorityQueue<Integer> large;  // min heap
        
        public MedianFinder() {
            small = new PriorityQueue<>((a, b) -> b - a);
            large = new PriorityQueue<>();
        }
        
        public void addNum(int num) {
            small.offer(num);
            large.offer(small.poll());
            
            if (small.size() < large.size()) {
                small.offer(large.poll());
            }
        }
        
        public double findMedian() {
            if (small.size() > large.size()) {
                return small.peek();
            }
            return (small.peek() + large.peek()) / 2.0;
        }
    }
    
    public static void main(String[] args) {
        // Test Kth Largest Element
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Kth largest element: " + findKthLargest(nums, k));
        
        // Test Last Stone Weight
        int[] stones = {2, 7, 4, 1, 8, 1};
        System.out.println("Last stone weight: " + lastStoneWeight(stones));
        
        // Test K Closest Points
        int[][] points = {{1,3}, {-2,2}, {5,-1}};
        int[][] closest = kClosest(points, 2);
        System.out.println("K closest points: " + 
                         Arrays.deepToString(closest));
        
        // Test MedianFinder
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after adding 1,2: " + 
                         medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println("Median after adding 3: " + 
                         medianFinder.findMedian());
    }
}