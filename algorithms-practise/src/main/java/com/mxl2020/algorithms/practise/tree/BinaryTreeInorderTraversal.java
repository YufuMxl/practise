package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 *
 * @see <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/">LeetCode 94</a>
 */
public class BinaryTreeInorderTraversal {

    private List<Integer> sequence;

    public List<Integer> inorderTraversal(TreeNode root) {
        sequence = new ArrayList<>();
        dfs(root);
        return sequence;
    }

    private void dfs(TreeNode node) {
        // 递归终止条件
        if (node == null) return;
        dfs(node.left);
        sequence.add(node.val);
        dfs(node.right);
    }
}
