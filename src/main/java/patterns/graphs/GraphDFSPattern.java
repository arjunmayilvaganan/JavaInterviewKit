package patterns.graphs;

import java.util.*;

public class GraphDFSPattern {
    /*
     * Graph DFS is used in problems like:
     * 1. Clone Graph
     * 2. Pacific Atlantic Water Flow
     * 3. Number of Connected Components
     * 4. Course Schedule
     */
    
    // Graph Node class for Clone Graph
    static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }
    
    // Example 1: Clone Graph (LeetCode 133)
    private static Map<Node, Node> visited = new HashMap<>();
    
    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        
        // If node was already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // Create clone node
        Node clone = new Node(node.val);
        visited.put(node, clone);
        
        // Clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        
        return clone;
    }
    
    // Example 2: Pacific Atlantic Water Flow (LeetCode 417)
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) return result;
        
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        
        // DFS from Pacific edges
        for (int i = 0; i < rows; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0, j);
        }
        
        // DFS from Atlantic edges
        for (int i = 0; i < rows; i++) {
            dfs(heights, atlantic, Integer.MIN_VALUE, i, cols-1);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, atlantic, Integer.MIN_VALUE, rows-1, j);
        }
        
        // Find cells that can reach both oceans
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private static void dfs(int[][] heights, boolean[][] visited, 
                          int prevHeight, int row, int col) {
        if (row < 0 || row >= heights.length || 
            col < 0 || col >= heights[0].length || 
            visited[row][col] || heights[row][col] < prevHeight) {
            return;
        }
        
        visited[row][col] = true;
        
        dfs(heights, visited, heights[row][col], row + 1, col);
        dfs(heights, visited, heights[row][col], row - 1, col);
        dfs(heights, visited, heights[row][col], row, col + 1);
        dfs(heights, visited, heights[row][col], row, col - 1);
    }
    
    // Example 3: Course Schedule (LeetCode 207)
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[0]).add(prereq[1]);
        }
        
        // 0 = unvisited, 1 = visiting, 2 = visited
        int[] visited = new int[numCourses];
        
        // Check each course
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, visited, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasCycle(List<List<Integer>> graph, 
                                  int[] visited, int course) {
        if (visited[course] == 1) return true;  // cycle detected
        if (visited[course] == 2) return false; // already visited
        
        visited[course] = 1; // mark as visiting
        
        for (int prereq : graph.get(course)) {
            if (hasCycle(graph, visited, prereq)) {
                return true;
            }
        }
        
        visited[course] = 2; // mark as visited
        return false;
    }
    
    public static void main(String[] args) {
        // Test Pacific Atlantic Water Flow
        int[][] heights = {
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        };
        List<List<Integer>> waterFlow = pacificAtlantic(heights);
        System.out.println("Cells that can flow to both oceans: " + waterFlow);
        
        // Test Course Schedule
        int[][] prerequisites = {{1,0}, {0,1}};
        System.out.println("Can finish courses: " + 
                         canFinish(2, prerequisites));
        
        // Test Clone Graph
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));
        
        Node cloned = cloneGraph(node1);
        System.out.println("Graph cloned successfully: " + 
                         (cloned != null && cloned.val == 1));
    }
}