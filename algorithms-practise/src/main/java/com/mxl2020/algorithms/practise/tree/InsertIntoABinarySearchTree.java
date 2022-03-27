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

        if (val < root.val) {
            if (root.left == null) root.left = new TreeNode(val);
            else insertIntoBST(root.left, val);
        } else if (val > root.val) {
            if (root.right == null) root.right = new TreeNode(val);
            else insertIntoBST(root.right, val);
        }

        return root;
    }

}
