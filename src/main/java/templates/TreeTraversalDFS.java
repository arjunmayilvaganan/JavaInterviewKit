package templates;

import java.util.ArrayList;
import java.util.List;

import datastructures.TreeNode;

/**
 * DFS Template for Binary Tree Traversal
 * Shows three types of DFS: preorder, inorder, and postorder
 */
public class TreeTraversalDFS {

    // Preorder DFS: Root -> Left -> Right
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderDFS(root, result);
        return result;
    }

    private void preorderDFS(TreeNode node, List<Integer> result) {
        // Base case: if node is null, return
        if (node == null) return;

        // Visit current node
        result.add(node.val);
        // Traverse left subtree
        preorderDFS(node.left, result);
        // Traverse right subtree
        preorderDFS(node.right, result);
    }

    // Inorder DFS: Left -> Root -> Right
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderDFS(root, result);
        return result;
    }

    private void inorderDFS(TreeNode node, List<Integer> result) {
        if (node == null) return;

        inorderDFS(node.left, result);
        result.add(node.val);
        inorderDFS(node.right, result);
    }

    // Postorder DFS: Left -> Right -> Root
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderDFS(root, result);
        return result;
    }

    private void postorderDFS(TreeNode node, List<Integer> result) {
        if (node == null) return;

        postorderDFS(node.left, result);
        postorderDFS(node.right, result);
        result.add(node.val);
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

        TreeTraversalDFS solution = new TreeTraversalDFS();

        // Test all three traversals
        System.out.println("Preorder traversal: " + solution.preorderTraversal(root));  // Expected: [1,2,4,5,3]
        System.out.println("Inorder traversal: " + solution.inorderTraversal(root));    // Expected: [4,2,5,1,3]
        System.out.println("Postorder traversal: " + solution.postorderTraversal(root)); // Expected: [4,5,2,3,1]
    }
}