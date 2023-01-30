package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @see <a href="https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">LeetCode 105</a>
 */
public class ConstructBinaryTreeFromPreAndInOrder {

    private int[] preorder;
    private Map<Integer, Integer> map;

    /**
     * @param preorder 前序遍历序列，可以确定根节点的位置
     * @param inorder  中序遍历序列，可以确定左右子树的范围
     * @return 返回被还原的树的根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        final int n = preorder.length;
        this.preorder = preorder;
        this.map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(0, n - 1, 0, n - 1);
    }

    private TreeNode buildTree(int ps, int pe, int is, int ie) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(preorder[ps]);
        int iRoot = map.get(preorder[ps]);

        root.left = buildTree(ps + 1, ps + (iRoot - is), is, iRoot - 1);
        root.right = buildTree(ps + (iRoot - is) + 1, pe, iRoot + 1, ie);

        return root;
    }

}
