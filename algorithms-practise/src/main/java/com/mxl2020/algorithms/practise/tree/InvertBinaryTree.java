package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 翻转二叉树
 *
 * @see <a href="https://leetcode-cn.com/problems/invert-binary-tree/">LeetCode 226</a>
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        // 递归终止条件：如果 root 为空，或者 root 是叶子节点，则不需要翻转，直接返回即可
        if (root == null || (root.left == null && root.right == null)) return root;

        // 翻转
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);

        return root;
    }
}
