package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 把二叉搜索树转换为累加树
 *
 * @see <a href="https://leetcode.cn/problems/convert-bst-to-greater-tree/">LeetCode 538</a>
 */
public class ConvertBSTtoGreaterTree {

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    /**
     * @param node 反向中序遍历
     */
    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.right);
        sum += node.val;
        node.val = sum;
        dfs(node.left);
    }
}
