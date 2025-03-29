package templates;

import java.util.*;

import datastructures.TreeNode;

/**
 * BFS Template for Binary Tree Level Order Traversal
 * Uses Queue data structure for level-by-level traversal
 */
public class TreeTraversalBFS {

    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Use Queue for BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // Get the number of nodes at current level
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes of current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                // Add left and right children to queue for next level
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }

    // Simpler BFS that returns nodes in level order as a single list
    public List<Integer> simpleBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        return result;
    }

    public static void main(String[] args) {
        // Create a sample binary tree:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeTraversalBFS solution = new TreeTraversalBFS();

        // Test level order traversal (by level)
        System.out.println("Level order traversal (by level): " + 
            solution.levelOrderTraversal(root));  // Expected: [[1], [2,3], [4,5]]

        // Test simple BFS
        System.out.println("Simple BFS traversal: " + 
            solution.simpleBFS(root));  // Expected: [1,2,3,4,5]
    }
}