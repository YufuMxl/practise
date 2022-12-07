package com.mxl2020.algorithms.practise.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存
 *
 * @see <a href="https://leetcode-cn.com/problems/lru-cache/">LeetCode 146</a>
 */
public class LRUCache {
    private int size = 0;
    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public int get(int key) {
        if (map.get(key) == null) return -1;

        Node node = map.get(key);
        node.pre.next = node.next;
        node.next.pre = node.pre;
        insertHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.get(key) == null) {
            if (this.size + 1 > capacity) {
                // 删除末尾元素
                map.remove(this.tail.pre.key);
                removeLast();
            } else this.size++;

            // 插入元素
            Node node = new Node(key, value);
            insertHead(node);
            map.put(key, node);
        } else {
            Node node = map.get(key);
            node.value = value;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            insertHead(node);
        }
    }

    private void insertHead(Node node) {
        node.pre = this.head;
        node.next = this.head.next;
        this.head.next.pre = node;
        this.head.next = node;
    }

    // 链表操作
    private void removeLast() {
        Node lastNode = this.tail.pre;
        lastNode.pre.next = lastNode.next;
        lastNode.next.pre = lastNode.pre;
        lastNode.pre = null;
        lastNode.next = null;
    }

    static class Node {
        private final int key;
        private int value;
        private Node next;
        private Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
