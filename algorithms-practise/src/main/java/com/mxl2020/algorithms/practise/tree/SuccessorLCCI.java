package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

import java.util.ArrayList;

/**
 * 后继者
 *
 * @see <a href="https://leetcode-cn.com/problems/successor-lcci/">LeetCode 面试题 04.06</a>
 */
public class SuccessorLCCI {

    private final ArrayList<TreeNode> route = new ArrayList<>();

    /**
     * @param root BST 的根节点
     * @param p    目标节点
     * @return 返回目标节点的"后继节点"
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 1.如果 p 有右子树，直接从右子树中寻找后继者
        if (p.right != null) return searchSuccessor(p);
        // 2.否则，从 root 到 p 的路径中寻找后继者
        genRoute(root, p.val);
        TreeNode ans = new TreeNode((int) 1e9);
        for (TreeNode node : route) {
            if (node.val > p.val && node.val < ans.val) ans = node;
        }

        if (ans.val == (int) 1e9) return null;
        else return ans;
    }

    private void genRoute(TreeNode node, int val) {
        if (node == null) return;
        if (node.val == val) return;
        route.add(node);
        if (node.val > val) genRoute(node.left, val);
        else genRoute(node.right, val);
    }

    private TreeNode searchSuccessor(TreeNode node) {
        TreeNode successor = node.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        return successor;
    }

}
