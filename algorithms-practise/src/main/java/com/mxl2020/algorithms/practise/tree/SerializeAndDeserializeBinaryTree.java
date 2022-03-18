package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * 二叉树的序列化与反序列化
 *
 * @see <a href="https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/">LeetCode 297</a>
 */
public class SerializeAndDeserializeBinaryTree {

    /**
     * 层序遍历序列 + 顺序存储法
     * <p>
     * 注意：该方法只能处理 31 层的树（如果用 long 来表示 index，则能处理 63 层树）
     *
     * @return 自定义序列化格式："0:0,1:1,2:2,3:3,4:4"
     */
    public String serialize(TreeNode root) {
        // 边界判断
        if (root == null) return "";
        // 初始化层序遍历序列的数组
        List<String> levelOrder = new ArrayList<>();
        // 初始化队列，队列中需要保存节点的 index
        Queue<Pair<Integer, TreeNode>> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(new Pair<>(0, root));

        while (!nodeQueue.isEmpty()) {
            Pair<Integer, TreeNode> nodePair = nodeQueue.poll();
            int nodeIndex = nodePair.getKey();
            TreeNode node = nodePair.getValue();
            levelOrder.add(nodeIndex + ":" + node.val);

            // 将左右子节点入队
            if (node.left != null) nodeQueue.offer(new Pair<>(nodeIndex * 2 + 1, node.left));
            if (node.right != null) nodeQueue.offer(new Pair<>(nodeIndex * 2 + 2, node.right));
        }

        return String.join(",", levelOrder);
    }

    /**
     * @param data 自定义序列化格式："0:0,1:1,2:2,3:3,4:4"
     */
    public TreeNode deserialize(String data) {
        // 边界判断
        if ("".equals(data)) return null;
        // 分割字符串
        String[] levelOrder = data.split(",");
        Map<Integer, TreeNode> parentNodeMap = new HashMap<>();

        for (String nodeIndexToVal : levelOrder) {
            int nodeIndex = Integer.parseInt(nodeIndexToVal.split(":")[0]);
            int nodeVal = Integer.parseInt(nodeIndexToVal.split(":")[1]);
            TreeNode currentNode = new TreeNode(nodeVal);
            parentNodeMap.put(nodeIndex, currentNode);
            if (nodeIndex != 0) {
                TreeNode parentNode = parentNodeMap.get((nodeIndex - 1) / 2);
                if (nodeIndex % 2 == 1) parentNode.left = currentNode;
                else parentNode.right = currentNode;
            }
        }
        return parentNodeMap.get(0);
    }

    /**
     * 前序遍历序列 + 递归
     *
     * @return 自定义序列化格式："1,2,3,null,null,null,null"
     */
    public String serialize2(TreeNode root) {
        preOrder = new ArrayList<>();
        dfs(root);
        return String.join(",", preOrder);
    }

    private List<String> preOrder;

    private void dfs(TreeNode node) {
        if (node == null) {
            preOrder.add("null");
            return;
        }
        preOrder.add(Integer.toString(node.val));
        dfs(node.left);
        dfs(node.right);
    }

    /**
     * @param data 自定义序列化格式："1,2,3,null,null,null,null"
     */
    public TreeNode deserialize2(String data) {
        sequence = data.split(",");
        current = 0;
        return restore();
    }

    private String[] sequence;
    private int current;

    private TreeNode restore() {
        if ("null".equals(sequence[current])) {
            current++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(sequence[current++]));
        node.left = restore();
        node.right = restore();
        return node;
    }

}
