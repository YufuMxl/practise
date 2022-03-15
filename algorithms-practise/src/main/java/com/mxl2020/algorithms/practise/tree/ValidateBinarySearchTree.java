package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 验证二叉搜索树
 *
 * @see <a href="https://leetcode-cn.com/problems/validate-binary-search-tree/">LeetCode 98</a>
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        // 递归终止条件：如果 root 为空，或者 root 是叶子节点，则一定是合法的 BST
        if (root == null || (root.left == null && root.right == null)) return true;

        // 验证 root 的左右子树的最大值和最小值是否满足 BST 的要求
        if (root.left != null && findMax(root.left) >= root.val) return false;
        if (root.right != null && findMin(root.right) <= root.val) return false;

        // 验证 root 节点的左右子树是不是合法的 BST
        return isValidBST(root.left) && isValidBST(root.right);
    }

    // 找一棵树的最大值
    private int findMax(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        if (node.left == null && node.right == null) return node.val;
        return Math.max(node.val, Math.max(findMax(node.left), findMax(node.right)));
    }

    // 找一棵树的最小值
    private int findMin(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        if (node.left == null && node.right == null) return node.val;
        return Math.min(node.val, Math.min(findMin(node.left), findMin(node.right)));
    }

}