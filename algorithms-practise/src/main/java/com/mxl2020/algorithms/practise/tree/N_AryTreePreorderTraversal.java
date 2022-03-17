package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * N 叉树的前序遍历
 *
 * @see <a href="https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/">LeetCode 589</a>
 */
public class N_AryTreePreorderTraversal {

    private List<Integer> sequence;

    public List<Integer> preorder(Node root) {
        sequence = new ArrayList<>();
        dfs(root);
        return sequence;
    }

    private void dfs(Node node) {
        // 递归终止条件
        if (node == null) return;
        sequence.add(node.val);
        if (node.children != null) {
            for (Node child : node.children) {
                dfs(child);
            }
        }
    }
}
