package templates;

public class BinarySearch {
    // Iterative Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if target is present at mid
            if (arr[mid] == target)
                return mid;
            
            // If target greater, ignore left half
            if (arr[mid] < target)
                left = mid + 1;
            
            // If target is smaller, ignore right half
            else
                right = mid - 1;
        }
        
        // If we reach here, then element was not present
        return -1;
    }
    
    // Recursive Binary Search
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            
            // If the element is present at the middle
            if (arr[mid] == target)
                return mid;
            
            // If element is smaller than mid, then it can only
            // be present in left subarray
            if (arr[mid] > target)
                return binarySearchRecursive(arr, target, left, mid - 1);
            
            // Else the element can only be present in right subarray
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
        
        // We reach here when element is not present in array
        return -1;
    }
    
    public static void main(String[] args) {
        // Test array (must be sorted for binary search)
        int[] arr = {2, 3, 4, 10, 40, 50, 60, 70, 80};
        int target = 10;
        
        // Test iterative binary search
        System.out.println("\nTesting Iterative Binary Search:");
        int result = binarySearch(arr, target);
        if (result == -1)
            System.out.println("Element " + target + " not present");
        else
            System.out.println("Element " + target + " found at index " + result);
            
        // Test recursive binary search
        System.out.println("\nTesting Recursive Binary Search:");
        result = binarySearchRecursive(arr, target, 0, arr.length - 1);
        if (result == -1)
            System.out.println("Element " + target + " not present");
        else
            System.out.println("Element " + target + " found at index " + result);
            
        // Test with element not in array
        target = 30;
        System.out.println("\nTesting with element not in array:");
        result = binarySearch(arr, target);
        if (result == -1)
            System.out.println("Element " + target + " not present");
        else
            System.out.println("Element " + target + " found at index " + result);
    }
}