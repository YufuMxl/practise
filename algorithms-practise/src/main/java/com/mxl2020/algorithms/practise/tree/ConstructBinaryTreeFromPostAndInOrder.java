package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 从中序与后序遍历序列构造二叉树
 *
 * @see <a href="https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">LeetCode 106</a>
 */
public class ConstructBinaryTreeFromPostAndInOrder {

    private int[] inorder;
    private int[] postorder;

    /**
     * 分治解法
     *
     * @param inorder   中序遍历序列，可以确定左右子树的范围
     * @param postorder 后序遍历序列，可以确定根节点的位置
     * @return 返回被还原的树的根节点
     * <p>
     * inorder 和 preorder 的长度相同，元素唯一
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return buildTree(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int inLeftIndex, int inRightIndex, int postLeftIndex, int postRightIndex) {
        // 递归终止条件
        if (inLeftIndex > inRightIndex) return null;

        // 获取根节点的值
        int rootValue = postorder[postRightIndex];
        TreeNode root = new TreeNode(rootValue);

        // 寻找根节点在中序遍历序列中的下标
        int rootIndexInInOrder = inLeftIndex;
        while (inorder[rootIndexInInOrder] != rootValue) {
            rootIndexInInOrder++;
        }

        // 递归生成树
        root.left = buildTree(
            inLeftIndex,
            rootIndexInInOrder - 1,
            postLeftIndex,
            postLeftIndex + rootIndexInInOrder - inLeftIndex - 1
        );
        root.right = buildTree(
            rootIndexInInOrder + 1,
            inRightIndex,
            postLeftIndex + rootIndexInInOrder - inLeftIndex,
            postRightIndex - 1
        );
        return root;
    }
}
