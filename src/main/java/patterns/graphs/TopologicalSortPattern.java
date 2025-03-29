package patterns.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSortPattern {
    /*
     * This pattern is used in problems like:
     * 1. Course Schedule (LeetCode 207)
     * 2. Course Schedule II (LeetCode 210)
     * 3. Alien Dictionary (LeetCode 269)
     */

    // Example 1: Topological Sort using Kahn's Algorithm
    // Given a number of courses and their prerequisites, return a valid order
    // to complete all courses, or an empty list if it's not possible.
    public static List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];

        // Build the graph and calculate in-degrees
        for (int[] prereq : prerequisites) {
            graph.computeIfAbsent(prereq[1], k -> new ArrayList<>()).add(prereq[0]);
            inDegree[prereq[0]]++;
        }

        // Add all nodes with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Perform BFS to generate the topological order
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result.add(course);
            if (graph.containsKey(course)) {
                for (int neighbor : graph.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        // If the result size is less than numCourses, there's a cycle
        return result.size() == numCourses ? result : new ArrayList<>();
    }

    public static void main(String[] args) {
        // Test Case 1: Valid topological order
        int[][] prerequisites1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Topological Order: " + topologicalSort(4, prerequisites1)); // [0, 1, 2, 3]

        // Test Case 2: Cycle in the graph
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Topological Order: " + topologicalSort(2, prerequisites2)); // []

        // Test Case 3: No prerequisites
        int[][] prerequisites3 = {};
        System.out.println("Topological Order: " + topologicalSort(3, prerequisites3)); // [0, 1, 2]
    }
}
