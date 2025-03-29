package patterns.trees;

import java.util.*;

public class TreePattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Invert Binary Tree
     * 2. Balanced Binary Tree
     * 3. Diameter of Binary Tree
     * 4. Lowest Common Ancestor
     */
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Example 1: Invert Binary Tree (LeetCode 226)
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        // Swap children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // Recursively invert subtrees
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
    
    // Example 2: Balanced Binary Tree (LeetCode 110)
    public static boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    
    private static int getHeight(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Example 3: Diameter of Binary Tree (LeetCode 543)
    private static int maxDiameter = 0;
    
    public static int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }
    
    private static int calculateHeight(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        
        // Update maxDiameter if current path is longer
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Example 4: Lowest Common Ancestor (LeetCode 236)
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left == null) return right;
        if (right == null) return left;
        
        return root; // Both nodes found, this is the LCA
    }
    
    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        
        // Test Invert Binary Tree
        System.out.println("Original tree:");
        printLevelOrder(root);
        TreeNode inverted = invertTree(root);
        System.out.println("\nInverted tree:");
        printLevelOrder(inverted);
        
        // Test Balanced Binary Tree
        System.out.println("\nIs balanced: " + isBalanced(root));
        
        // Test Diameter of Binary Tree
        System.out.println("Diameter: " + diameterOfBinaryTree(root));
        
        // Test Lowest Common Ancestor
        TreeNode p = root.left;
        TreeNode q = root.right;
        TreeNode lca = lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + ": " + lca.val);
    }
    
    // Helper method to print tree level by level
    private static void printLevelOrder(TreeNode root) {
        if (root == null) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }
}