package patterns.treedfs;

import java.util.*;

public class TreeDFSPattern {
    /*
     * This pattern is used in problems like:
     * 1. Maximum Depth of Binary Tree
     * 2. Same Tree
     * 3. Path Sum
     * 4. Binary Tree Maximum Path Sum
     */
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Example 1: Maximum Depth of Binary Tree (LeetCode 104)
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    // Example 2: Same Tree (LeetCode 100)
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // If both nodes are null, trees are same
        if (p == null && q == null) return true;
        
        // If one node is null and other isn't, trees are different
        if (p == null || q == null) return false;
        
        // Check current nodes and recursively check subtrees
        return p.val == q.val && 
               isSameTree(p.left, q.left) && 
               isSameTree(p.right, q.right);
    }
    
    // Example 3: Path Sum (LeetCode 112)
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        // If it's a leaf node, check if the path sum equals target
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        // Recursively check left and right subtrees
        return hasPathSum(root.left, targetSum - root.val) || 
               hasPathSum(root.right, targetSum - root.val);
    }
    
    // Example 4: Binary Tree Maximum Path Sum (LeetCode 124)
    private static int maxSum = Integer.MIN_VALUE;
    
    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }
    
    private static int maxPathSumHelper(TreeNode node) {
        if (node == null) return 0;
        
        // Get max path sum from left and right subtrees
        int leftSum = Math.max(maxPathSumHelper(node.left), 0);
        int rightSum = Math.max(maxPathSumHelper(node.right), 0);
        
        // Update maxSum if current path is larger
        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);
        
        // Return max path sum ending at current node
        return Math.max(leftSum, rightSum) + node.val;
    }
    
    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        // Test maxDepth
        System.out.println("Maximum depth: " + maxDepth(root));
        
        // Test isSameTree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        System.out.println("Are trees same? " + isSameTree(root, root2));
        
        // Test hasPathSum
        System.out.println("Has path sum 7? " + hasPathSum(root, 7));
        
        // Test maxPathSum
        System.out.println("Maximum path sum: " + maxPathSum(root));
    }
}