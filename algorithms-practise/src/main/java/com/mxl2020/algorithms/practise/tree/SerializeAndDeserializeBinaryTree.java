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
     * 注意：该方法只能处理 31 层的树
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
     * 前序遍历序列
     *
     * @return 1, 2, null, null, 3, 4, null, null, 5, null, null
     */
    public String serialize2(TreeNode root) {
        return "";
    }

    /**
     * @param data 序列化的二叉树（自定义格式："1,2,3,null,null,4,5"）
     */
    public TreeNode deserialize2(String data) {
        return null;
    }

}
