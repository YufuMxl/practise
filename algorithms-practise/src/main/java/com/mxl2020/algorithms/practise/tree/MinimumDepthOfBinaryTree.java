package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 二叉树的最小深度
 *
 * @see <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/">LeetCode 111</a>
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        // 特别注意，假设 root 的左子树为空，那么 root 的最小深度就是右子树的最小深度 + 1
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
