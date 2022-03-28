package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;
import javafx.util.Pair;

/**
 * 删除二叉搜索树中的节点
 *
 * @see <a href="https://leetcode-cn.com/problems/delete-node-in-a-bst/">LeetCode 450</a>
 */
public class DeleteNodeInBST {

    /**
     * @param root BST 的根节点
     * @param key  被删除的目标值
     * @return 返回根节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode soldier = new TreeNode(Integer.MAX_VALUE);
        soldier.left = root;

        Pair<TreeNode, TreeNode> target = searchNodeAndFather(root, soldier, key);
        if (target != null) {
            TreeNode treeNode = target.getKey();
            TreeNode treeNodeFather = target.getValue();
            if (treeNode.left != null && treeNode.right != null) {
                // 如果被删除的节点同时有左右子树
                // 1.寻找后继节点
                TreeNode successorFather = treeNode;
                TreeNode successor = treeNode.right;
                while (successor.left != null) {
                    successorFather = successor;
                    successor = successor.left;
                }
                // 2.当前节点赋值为后继节点的值
                treeNode.val = successor.val;
                // 3.删除后继节点（特别注意：后继节点最多有一个右子树）
                if (successor == successorFather.left) successorFather.left = successor.right;
                else successorFather.right = successor.right;
            } else {
                // 如果被删除的节点最多有一个子树
                TreeNode subTree = null;
                if (treeNode.left != null) subTree = treeNode.left;
                if (treeNode.right != null) subTree = treeNode.right;
                if (treeNode == treeNodeFather.left) treeNodeFather.left = subTree;
                else treeNodeFather.right = subTree;
            }
        }

        return soldier.left;
    }

    private Pair<TreeNode, TreeNode> searchNodeAndFather(TreeNode node, TreeNode father, int key) {
        if (node == null) return null;
        if (key == node.val) return new Pair<>(node, father);
        if (key > node.val) return searchNodeAndFather(node.right, node, key);
        else return searchNodeAndFather(node.left, node, key);
    }

}
