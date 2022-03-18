package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @see <a href="https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">LeetCode 105</a>
 */
public class ConstructBinaryTreeFromPreAndInOrder {
    // 注意：给定前序和后序，不能唯一确定一棵二叉树

    private int[] preorder;
    private int[] inorder;

    /**
     * 分治解法
     *
     * @param preorder 前序遍历序列，可以确定根节点的位置
     * @param inorder  中序遍历序列，可以确定左右子树的范围
     * @return 返回被还原的树的根节点
     * <p>
     * preorder 和 inorder 的长度相同，元素唯一
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int preLeftIndex, int preRightIndex, int inLeftIndex, int inRightIndex) {
        // 递归终止条件
        if (preLeftIndex > preRightIndex) return null;
        // 生成根节点
        int rootValue = this.preorder[preLeftIndex];
        TreeNode root = new TreeNode(rootValue);

        // 找到根节点在中序遍历序列中的下标
        // 根据 rootIndexInInOrder 可以定位左右子树的范围
        int rootIndexInInOrder = inLeftIndex;
        while (inorder[rootIndexInInOrder] != rootValue) {
            rootIndexInInOrder++;
        }

        // 递归构建左子树
        root.left = buildTree(
            preLeftIndex + 1,
            preLeftIndex + rootIndexInInOrder - inLeftIndex,
            inLeftIndex,
            rootIndexInInOrder - 1
        );
        // 递归构建右子树
        root.right = buildTree(
            preRightIndex + rootIndexInInOrder - inRightIndex + 1,
            preRightIndex,
            rootIndexInInOrder + 1,
            inRightIndex
        );

        // 返回根节点
        return root;
    }

}
