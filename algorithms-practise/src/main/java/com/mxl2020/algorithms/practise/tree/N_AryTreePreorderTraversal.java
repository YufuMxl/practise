package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * N 叉树的前序遍历
 *
 * @see <a href="https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/">LeetCode 589</a>
 */
public class N_AryTreePreorderTraversal {

    private List<Integer> sequence;

    /**
     * 递归
     *
     * @param root N 叉树的根结点
     * @return 返回前序遍历的结果
     */
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

    /**
     * 迭代 + 栈
     */
    public List<Integer> preorder2(Node root) {
        sequence = new ArrayList<>();
        // 初始化栈
        Deque<Node> nodeStack = new ArrayDeque<>();
        // 将根结点入栈
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node popNode = nodeStack.pop();
            sequence.add(popNode.val);
            if (popNode.children != null) {
                for (int i = popNode.children.size() - 1; i >= 0; i--) {
                    nodeStack.push(popNode.children.get(i));
                }
            }
        }
        return sequence;
    }
}
