package patterns.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class PrefixSumPattern {
    /*
     * This pattern is used in problems like:
     * 1. Subarray Sum Equals K (LeetCode 560)
     * 2. Continuous Subarray Sum (LeetCode 523)
     * 3. Range Sum Query - Immutable (LeetCode 303)
     * 4. Maximum Size Subarray Sum Equals K (LeetCode 325)
     */

    // Example 1: Subarray Sum Equals K (LeetCode 560)
    // Count the number of subarrays that sum to a given value k.
    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1); // Base case: a sum of 0 occurs once

        for (int num : nums) {
            sum += num;
            count += prefixSum.getOrDefault(sum - k, 0);
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    // Example 2: Range Sum Query - Immutable (LeetCode 303)
    // Precompute prefix sums to efficiently calculate the sum of any subarray.
    static class NumArray {
        private int[] prefixSum;

        public NumArray(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }

    public static void main(String[] args) {
        // Test Subarray Sum Equals K
        int[] nums1 = {1, 1, 1};
        System.out.println("Subarray Sum Equals K: " + subarraySum(nums1, 2)); // 2

        int[] nums2 = {1, 2, 3};
        System.out.println("Subarray Sum Equals K: " + subarraySum(nums2, 3)); // 2

        // Test Range Sum Query - Immutable
        int[] nums3 = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums3);
        System.out.println("Range Sum (0, 2): " + numArray.sumRange(0, 2)); // 1
        System.out.println("Range Sum (2, 5): " + numArray.sumRange(2, 5)); // -1
        System.out.println("Range Sum (0, 5): " + numArray.sumRange(0, 5)); // -3
    }
}
