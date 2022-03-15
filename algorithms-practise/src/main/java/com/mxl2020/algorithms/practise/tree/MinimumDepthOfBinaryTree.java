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

    /**
     * 解法 2：递归 + 全局变量
     *
     * @param root 树的节点
     * @return 返回 root 所代表的树的最小深度
     */
    public int minDepth2(TreeNode root) {
        // 由于 root 为 null 时，拿不到叶子节点，无法更新 result 的值，所以当 root 为 null 时，直接返回 0 即可
        if (root == null) return 0;
        calculateDepth(root);
        return result;
    }

    private int depth = 0;
    private int result = Integer.MAX_VALUE;

    private void calculateDepth(TreeNode root) {
        // 递归出界，啥也不干
        if (root == null) return;
        // 只有遇到叶子节点的时候，才更新答案
        if (root.right == null && root.left == null) {
            result = Math.min(result, depth + 1);
            return;
        }
        depth++;
        calculateDepth(root.left);
        calculateDepth(root.right);
        depth--;
    }
}
