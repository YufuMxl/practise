package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.Node;

public class BinarySearchTree {
    // 根节点
    private Node tree;

    // 查找操作
    public Node find(int data, Node node) {
        // 终止条件
        if (node == null) return null;
        if (node.data == data) return node;
        if (node.data > data) {
            return find(data, node.left);
        } else {
            return find(data, node.right);
        }
    }

    // 插入操作
    public Node insert(int data, Node node) {
        if (node == null) return new Node(data);

        if (node.data > data) {
            if (node.left == null) {
                Node newNode = new Node(data);
                node.left = newNode;
                return newNode;
            } else {
                return insert(data, node.left);
            }
        } else {
            if (node.right == null) {
                Node newNode = new Node(data);
                node.right = newNode;
                return newNode;
            } else {
                return insert(data, node.right);
            }
        }
    }

    // 删除操作
    public void delete(int data) {
        Node parent = null;
        Node currentNode = tree;

        while (currentNode != null) {
            // 找到要删除的节点，跳出循环
            if (currentNode.data == data) break;

            parent = currentNode;
            if (currentNode.data > data) currentNode = currentNode.left;
            else currentNode = currentNode.right;
        }
        // 没有找到要删除的节点，直接返回
        if (currentNode == null) return;

        // 如果要删除的节点有两个子节点
        if (currentNode.left != null && currentNode.right != null) {
            // 寻找右子树的最小节点
            Node minNode = currentNode.right;
            Node minNodeParent = currentNode;
            while (minNode.left != null) {
                minNodeParent = minNode;
                minNode = minNode.left;
            }
            // 将要删除节点的值设为最小节点的值
            currentNode.data = minNode.data;
            // 将原来的最小节点删除
            if (minNodeParent.left == minNode) minNodeParent.left = null;
            else minNodeParent.right = null;
            return;
        }

        // 如果要删除的节点没有子节点，或者只有一个子节点
        Node child;
        if (currentNode.left != null) child = currentNode.left;
        else if (currentNode.right != null) child = currentNode.right;
        else child = null;

        if (parent == null) {
            // 如果删除的节点是根节点
            tree = child;
        } else {
            if (parent.left == currentNode) parent.left = child;
            else parent.right = child;
        }

    }
}
