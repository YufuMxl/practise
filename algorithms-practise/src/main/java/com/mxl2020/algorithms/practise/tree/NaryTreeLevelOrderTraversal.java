package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.Node;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * N 叉树的层序遍历
 *
 * @see <a href="https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/">LeetCode 429</a>
 */
public class NaryTreeLevelOrderTraversal {
    /**
     * 层序遍历，使用先进先出的队列
     *
     * @param root N 叉树的根节点
     * @return 返回层序遍历的结果
     */
    public List<List<Integer>> levelOrder(Node root) {
        // 初始化返回值
        List<List<Integer>> sequence = new ArrayList<>();
        if (root == null) return sequence;
        // 初始化队列
        // 返回值类型决定了我们需要同时保存节点的深度
        Queue<Pair<Node, Integer>> nodeQueue = new ArrayDeque<>();
        // 将根节点入队
        nodeQueue.offer(new Pair<>(root, 0));
        while (!nodeQueue.isEmpty()) {
            Pair<Node, Integer> nodePair = nodeQueue.poll();
            Node node = nodePair.getKey();
            Integer depth = nodePair.getValue();
            // 将当前结点的内容存入 sequence 数组中
            if (sequence.size() <= depth) sequence.add(new ArrayList<>());
            sequence.get(depth).add(node.val);

            // 将当前节点的子节点入队
            for (Node child : node.children) {
                nodeQueue.offer(new Pair<>(child, depth + 1));
            }
        }
        return sequence;
    }
}
