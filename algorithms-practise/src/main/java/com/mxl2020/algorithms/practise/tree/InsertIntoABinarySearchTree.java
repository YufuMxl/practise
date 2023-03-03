package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 二叉搜索树中的插入操作
 *
 * @see <a href="https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/">LeetCode 701</a>
 */
public class InsertIntoABinarySearchTree {

    /**
     * @param root BST 的根节点
     * @param val  需要插入 BST 的值
     * @return 返回 BST 根节点
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        dfsInsert(root, val);
        return root;
    }

    private void dfsInsert(TreeNode node, int val) {
        if (val < node.val) {
            if (node.left == null) node.left = new TreeNode(val);
            else dfsInsert(node.left, val);
        } else {
            if (node.right == null) node.right = new TreeNode(val);
            else dfsInsert(node.right, val);
        }
    }

}
