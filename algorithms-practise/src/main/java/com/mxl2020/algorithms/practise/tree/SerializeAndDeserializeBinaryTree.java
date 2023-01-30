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
     * 层序遍历
     *
     * @return 序列化格式："a:b:c:d"，其中，a 为节点 id，b 为父节点 id，c 标明节点是左右节点，d 为节点值
     */
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<Pair<int[], TreeNode>> nodeQueue = new ArrayDeque<>();
        int order = 0;
        nodeQueue.offer(new Pair<>(new int[]{order, -1, -1}, root));
        while (!nodeQueue.isEmpty()) {
            Pair<int[], TreeNode> nodePair = nodeQueue.poll();
            int id = nodePair.getKey()[0];
            int parentID = nodePair.getKey()[1];
            int lor = nodePair.getKey()[2];
            TreeNode node = nodePair.getValue();

            sb.append(id).append(":").append(parentID).append(":").append(lor).append(":").append(node.val).append(",");

            if (node.left != null) nodeQueue.offer(new Pair<>(new int[]{++order, id, 0}, node.left));
            if (node.right != null) nodeQueue.offer(new Pair<>(new int[]{++order, id, 1}, node.right));
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if ("".equals(data)) return null;
        String[] arr = data.split(",");
        Map<Integer, TreeNode> map = new HashMap<>();
        for (String nodeStr : arr) {
            String[] nodeStrArr = nodeStr.split(":");
            int id = Integer.parseInt(nodeStrArr[0]);
            int parentID = Integer.parseInt(nodeStrArr[1]);
            int lor = Integer.parseInt(nodeStrArr[2]);
            TreeNode node = new TreeNode(Integer.parseInt(nodeStrArr[3]));
            map.put(id, node);

            if (id != 0) {
                TreeNode parent = map.get(parentID);
                if (lor == 0) parent.left = node;
                else parent.right = node;
            }
        }
        return map.get(0);
    }

    /**
     * @return 前序遍历序列
     */
    public String serialize2(TreeNode root) {
        sb = new StringBuilder();
        dfs(root);
        return sb.toString();
    }

    private StringBuilder sb;

    private void dfs(TreeNode node) {
        if (node == null) {
            sb.append("n,");
            return;
        }
        sb.append(node.val).append(",");
        dfs(node.left);
        dfs(node.right);
    }

    public TreeNode deserialize2(String data) {
        index = 0;
        sequence = data.split(",");
        return restructure();
    }

    private String[] sequence;
    private int index;

    private TreeNode restructure() {
        if ("n".equals(sequence[index])) {
            index++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(sequence[index]));
        index++;
        node.left = restructure();
        node.right = restructure();
        return node;
    }

}
