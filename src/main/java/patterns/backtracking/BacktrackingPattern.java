package patterns.backtracking;

import java.util.*;

public class BacktrackingPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Subsets
     * 2. Combination Sum
     * 3. Permutations
     * 4. N-Queens
     */
    
    // Example 1: Subsets (LeetCode 78)
    public static List<List<Integer>> subsetsDFS(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static List<List<Integer>> subsetsBFS(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Start with empty subset
        result.add(new ArrayList<>());

        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                // Create a new subset by copying an existing one
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                // Add current element to the new subset
                newSubset.add(num);
                // Add the new subset to result
                result.add(newSubset);
            }
        }

        return result;
    }
    
    // Example 2: Combination Sum (LeetCode 39)
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombSum(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    
    private static void backtrackCombSum(List<List<Integer>> result, List<Integer> tempList, 
                                        int[] candidates, int remain, int start) {
        if (remain < 0) return;
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            tempList.add(candidates[i]);
            backtrackCombSum(result, tempList, candidates, remain - candidates[i], i);
            tempList.remove(tempList.size() - 1);
        }
    }
    
    // Example 3: Permutations (LeetCode 46)
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermute(result, new ArrayList<>(), nums);
        return result;
    }
    
    private static void backtrackPermute(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            backtrackPermute(result, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }
    
    // Example 4: N-Queens (LeetCode 51)
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrackNQueens(result, board, 0);
        return result;
    }
    
    private static void backtrackNQueens(List<List<String>> result, char[][] board, int row) {
        if (row == board.length) {
            result.add(constructBoard(board));
            return;
        }
        
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrackNQueens(result, board, row + 1);
                board[row][col] = '.';
            }
        }
    }
    
    private static boolean isValid(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        
        // Check diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        
        // Check anti-diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        
        return true;
    }
    
    private static List<String> constructBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
    
    public static void main(String[] args) {
        // Test Subsets
        int[] nums1 = {1, 2, 3};
        System.out.println("SubsetsDFS: " + subsetsDFS(nums1));

        System.out.println("SubsetsBFS: " + subsetsBFS(nums1));
        
        // Test Combination Sum
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("Combination Sum: " + combinationSum(candidates, target));
        
        // Test Permutations
        int[] nums2 = {1, 2, 3};
        System.out.println("Permutations: " + permute(nums2));
        
        // Test N-Queens
        int n = 4;
        System.out.println("N-Queens solutions: " + solveNQueens(n));
    }
}