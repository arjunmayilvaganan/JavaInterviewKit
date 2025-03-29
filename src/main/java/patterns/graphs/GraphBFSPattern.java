package patterns.graphs;

import java.util.*;

public class GraphBFSPattern {
    /*
     * Graph BFS is used in problems like:
     * 1. Number of Islands
     * 2. Word Ladder
     * 3. Rotting Oranges
     * 4. Shortest Path in Binary Matrix
     */
    
    // Example 1: Number of Islands (LeetCode 200)
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private static void bfs(char[][] grid, int row, int col) {
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        grid[row][col] = '0'; // mark as visited
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            for (int[] dir : directions) {
                int newRow = curr[0] + dir[0];
                int newCol = curr[1] + dir[1];
                
                if (newRow >= 0 && newRow < grid.length &&
                    newCol >= 0 && newCol < grid[0].length &&
                    grid[newRow][newCol] == '1') {
                    
                    grid[newRow][newCol] = '0';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
    
    // Example 2: Rotting Oranges (LeetCode 994)
    public static int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Find all rotten oranges and count fresh ones
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        
        if (fresh == 0) return 0;
        if (queue.isEmpty()) return -1;
        
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int minutes = -1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                
                for (int[] dir : directions) {
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];
                    
                    if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {
                        
                        grid[newRow][newCol] = 2;
                        fresh--;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }
        
        return fresh == 0 ? minutes : -1;
    }
    
    // Example 3: Shortest Path in Binary Matrix (LeetCode 1091)
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0][0] == 1) return -1;
        
        int n = grid.length;
        if (grid[n-1][n-1] == 1) return -1;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1; // mark as visited
        
        int[][] directions = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},          {0,1},
            {1,-1},  {1,0},  {1,1}
        };
        
        int length = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                
                if (curr[0] == n-1 && curr[1] == n-1) {
                    return length;
                }
                
                for (int[] dir : directions) {
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];
                    
                    if (newRow >= 0 && newRow < n &&
                        newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] == 0) {
                        
                        grid[newRow][newCol] = 1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
            
            length++;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        // Test Number of Islands
        char[][] islandGrid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Number of islands: " + numIslands(islandGrid));
        
        // Test Rotting Oranges
        int[][] orangeGrid = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        System.out.println("Minutes until all oranges rotten: " + 
                         orangesRotting(orangeGrid));
        
        // Test Shortest Path in Binary Matrix
        int[][] binaryMatrix = {
            {0,1,1,0},
            {0,0,0,1},
            {1,1,0,0},
            {1,0,0,0}
        };
        System.out.println("Shortest path length: " + 
                         shortestPathBinaryMatrix(binaryMatrix));
    }
}