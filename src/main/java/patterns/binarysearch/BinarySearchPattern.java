package patterns.binarysearch;

public class BinarySearchPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Binary Search
     * 2. Search in Rotated Sorted Array
     * 3. Find Minimum in Rotated Sorted Array
     * 4. Search a 2D Matrix
     */
    
    // Example 1: Binary Search (LeetCode 704)
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // Example 2: Search in Rotated Sorted Array (LeetCode 33)
    public static int searchRotated(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // Example 3: Find Minimum in Rotated Sorted Array (LeetCode 153)
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }
    
    // Example 4: Search a 2D Matrix (LeetCode 74)
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int element = matrix[mid / n][mid % n];
            
            if (element == target) {
                return true;
            } else if (element < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        // Test Binary Search
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println("Binary Search: " + binarySearch(nums1, target1));
        
        // Test Search in Rotated Sorted Array
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 0;
        System.out.println("Search in Rotated Array: " + searchRotated(nums2, target2));
        
        // Test Find Minimum in Rotated Sorted Array
        int[] nums3 = {3, 4, 5, 1, 2};
        System.out.println("Minimum in Rotated Array: " + findMin(nums3));
        
        // Test Search a 2D Matrix
        int[][] matrix = {
            {1,  3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target3 = 3;
        System.out.println("Search in Matrix: " + searchMatrix(matrix, target3));
    }
}