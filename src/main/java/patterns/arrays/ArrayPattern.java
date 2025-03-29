package patterns.arrays;

import java.util.*;

public class ArrayPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Two Sum
     * 2. Best Time to Buy and Sell Stock
     * 3. Contains Duplicate
     * 4. Product of Array Except Self
     */
    
    // Example 1: Two Sum (LeetCode 1)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        return new int[]{};
    }
    
    // Example 2: Best Time to Buy and Sell Stock (LeetCode 121)
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        
        return maxProfit;
    }
    
    // Example 3: Contains Duplicate (LeetCode 217)
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        
        return false;
    }
    
    // Example 4: Product of Array Except Self (LeetCode 238)
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i-1] * nums[i-1];
        }
        
        // Calculate right products and combine
        int rightProduct = 1;
        for (int i = n-1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }
    
    // Example 5: Maximum Subarray (LeetCode 53)
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    // Example 6: Maximum Product Subarray (LeetCode 152)
    public static int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;
        
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
            minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));
            
            maxSoFar = tempMax;
            result = Math.max(result, maxSoFar);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test Two Sum
        int[] nums1 = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Two Sum: " + 
                         Arrays.toString(twoSum(nums1, target)));
        
        // Test Best Time to Buy and Sell Stock
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit: " + maxProfit(prices));
        
        // Test Contains Duplicate
        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Contains Duplicate: " + 
                         containsDuplicate(nums2));
        
        // Test Product of Array Except Self
        int[] nums3 = {1, 2, 3, 4};
        System.out.println("Product Except Self: " + 
                         Arrays.toString(productExceptSelf(nums3)));
        
        // Test Maximum Subarray
        int[] nums4 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + 
                         maxSubArray(nums4));
        
        // Test Maximum Product Subarray
        int[] nums5 = {2, 3, -2, 4};
        System.out.println("Maximum Product Subarray: " + 
                         maxProduct(nums5));
    }
}