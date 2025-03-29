package patterns.slidingwindow;

import java.util.*;

public class SlidingWindowPattern {
    /*
     * This pattern is used in problems like:
     * 1. Maximum sum subarray of size k
     * 2. Longest substring with k distinct characters
     * 3. NeetCode: Longest Substring Without Repeating Characters
     * 4. NeetCode: Best Time to Buy and Sell Stock
     */
    
    // Example 1: Find max sum subarray of size k
    public static int findMaxSumSubarray(int[] arr, int k) {
        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;
        
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            
            // slide the window if we hit the window size of k
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window
            }
        }
        
        return maxSum;
    }
    
    // Example 2: Longest Substring Without Repeating Characters (LeetCode 3)
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int windowStart = 0;
        
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            
            // if we've seen this char before, update windowStart
            if (charIndexMap.containsKey(rightChar)) {
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }
            
            charIndexMap.put(rightChar, windowEnd);
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        
        return maxLength;
    }
    
    // Example 3: Best Time to Buy and Sell Stock (LeetCode 121)
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        
        return maxProfit;
    }
    
    public static void main(String[] args) {
        // Test maxSumSubarray
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Max sum subarray of size " + k + ": " + findMaxSumSubarray(arr, k));
        
        // Test lengthOfLongestSubstring
        String s = "abcabcbb";
        System.out.println("Length of longest substring without repeating characters: " 
                         + lengthOfLongestSubstring(s));
        
        // Test maxProfit
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum profit: " + maxProfit(prices));
    }
}