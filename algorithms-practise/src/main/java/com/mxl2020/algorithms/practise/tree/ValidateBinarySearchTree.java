package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 验证二叉搜索树
 *
 * @see <a href="https://leetcode-cn.com/problems/validate-binary-search-tree/">LeetCode 98</a>
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return root.val > findMax(root.left) && root.val < findMin(root.right) && isValidBST(root.left) && isValidBST(root.right);
    }

    private long findMax(TreeNode node) {
        if (node == null) return Long.MIN_VALUE;
        return Math.max(node.val, Math.max(findMax(node.left), findMax(node.right)));
    }

    private long findMin(TreeNode node) {
        if (node == null) return Long.MAX_VALUE;
        return Math.min(node.val, Math.min(findMin(node.left), findMin(node.right)));
    }

    private long pre = Long.MIN_VALUE;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        // 访问左子树
        if (!isValidBST(root.left)) return false;
        // 访问当前节点
        if (root.val <= pre) return false;
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

}
