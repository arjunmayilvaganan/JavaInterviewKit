package patterns.dynamicprogramming;

import java.util.*;

public class DynamicProgrammingPattern {
    /*
     * This pattern is used in problems like:
     * 1. Climbing Stairs
     * 2. House Robber
     * 3. Longest Increasing Subsequence
     * 4. Coin Change
     */
    
    // Example 1: Climbing Stairs (LeetCode 70)
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
    
    // Example 2: House Robber (LeetCode 198)
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        
        return dp[nums.length - 1];
    }
    
    // Example 3: Longest Increasing Subsequence (LeetCode 300)
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
    
    // Example 4: Coin Change (LeetCode 322)
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    public static void main(String[] args) {
        // Test Climbing Stairs
        int n = 5;
        System.out.println("Number of ways to climb " + n + " stairs: " + climbStairs(n));
        
        // Test House Robber
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("Maximum amount that can be robbed: " + rob(houses));
        
        // Test Longest Increasing Subsequence
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of longest increasing subsequence: " + lengthOfLIS(nums));
        
        // Test Coin Change
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum coins needed for amount " + amount + ": " + 
                         coinChange(coins, amount));
    }
}