package patterns.dynamicprogramming;

public class TwoDArrayDPPattern {
    /*
     * This pattern is used in problems like:
     * 1. Unique Paths (LeetCode 62)
     * 2. Minimum Path Sum (LeetCode 64)
     * 3. Edit Distance (LeetCode 72)
     * 4. Longest Common Subsequence (LeetCode 1143)
     */

    // Example 1: Unique Paths (LeetCode 62)
    // Find the number of unique paths from the top-left corner to the bottom-right corner
    // of an m x n grid, moving only down or right.
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Initialize the first row and first column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    // Example 2: Minimum Path Sum (LeetCode 64)
    // Find the minimum path sum from the top-left corner to the bottom-right corner
    // of an m x n grid, where each cell contains a non-negative integer.
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Fill the first row
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        // Fill the first column
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // Test Unique Paths
        System.out.println("Unique Paths (3x7 grid): " + uniquePaths(3, 7)); // 28
        System.out.println("Unique Paths (3x2 grid): " + uniquePaths(3, 2)); // 3

        // Test Minimum Path Sum
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println("Minimum Path Sum: " + minPathSum(grid)); // 7
    }
}
