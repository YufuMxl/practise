package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 二叉树的最大深度
 *
 * @see <a href="https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/">LeetCode 104</a>
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 解法 2：递归 + 全局变量
     *
     * @param root 树的节点
     * @return 返回 root 所代表的树的最大深度
     */
    public int maxDepth2(TreeNode root) {
        calculateDepth(root);
        return result;
    }

    private int depth = 0;
    private int result = 0;

    private void calculateDepth(TreeNode root) {
        if (root == null) {
            // 递归出界，更新答案
            result = Math.max(depth, result);
            return;
        }
        depth++;
        calculateDepth(root.left);
        calculateDepth(root.right);
        depth--;
    }
}
