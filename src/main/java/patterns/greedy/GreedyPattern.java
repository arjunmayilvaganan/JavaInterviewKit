package patterns.greedy;

import java.util.*;

public class GreedyPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Maximum Subarray
     * 2. Jump Game
     * 3. Gas Station
     * 4. Hand of Straights
     */
    
    // Example 1: Maximum Subarray (LeetCode 53)
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    // Example 2: Jump Game (LeetCode 55)
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i <= maxReach && i < nums.length; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        
        return false;
    }
    
    // Example 3: Gas Station (LeetCode 134)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;
        int surplus = 0;
        int start = 0;
        
        for (int i = 0; i < gas.length; i++) {
            totalSurplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            
            if (surplus < 0) {
                surplus = 0;
                start = i + 1;
            }
        }
        
        return totalSurplus >= 0 ? start : -1;
    }
    
    // Example 4: Hand of Straights (LeetCode 846)
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        
        TreeMap<Integer, Integer> cardCount = new TreeMap<>();
        for (int card : hand) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }
        
        while (!cardCount.isEmpty()) {
            int first = cardCount.firstKey();
            
            for (int i = 0; i < groupSize; i++) {
                int currentCard = first + i;
                if (!cardCount.containsKey(currentCard)) {
                    return false;
                }
                
                cardCount.put(currentCard, cardCount.get(currentCard) - 1);
                if (cardCount.get(currentCard) == 0) {
                    cardCount.remove(currentCard);
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Test Maximum Subarray
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum: " + maxSubArray(nums1));
        
        // Test Jump Game
        int[] nums2 = {2, 3, 1, 1, 4};
        System.out.println("Can jump to end: " + canJump(nums2));
        
        // Test Gas Station
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("Starting station: " + canCompleteCircuit(gas, cost));
        
        // Test Hand of Straights
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        System.out.println("Can be divided into straight hands: " + 
                         isNStraightHand(hand, groupSize));
    }
}