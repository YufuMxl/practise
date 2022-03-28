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
        return inorderSuccessor(root, p.val);
    }

    /**
     * 通用方法：求 targetValue 的后继者
     */
    public TreeNode inorderSuccessor(TreeNode root, int targetValue) {
        TreeNode targetNode = searchAndGenRoute(root, targetValue);
        if (targetNode != null && targetNode.right != null) {
            TreeNode successor = targetNode.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        return searchSuccessor(targetValue);
    }

    /**
     * 查询目标节点并保存查询路径
     */
    private TreeNode searchAndGenRoute(TreeNode node, int targetValue) {
        if (node == null) return null;

        route.add(node);
        if (targetValue == node.val) return node;
        if (targetValue > node.val) return searchAndGenRoute(node.right, targetValue);
        else return searchAndGenRoute(node.left, targetValue);
    }

    private TreeNode searchSuccessor(int targetValue) {
        TreeNode successor = null;
        for (TreeNode treeNode : route) {
            if (treeNode.val > targetValue && (successor == null || successor.val > treeNode.val)) {
                successor = treeNode;
            }
        }
        return successor;
    }

}
