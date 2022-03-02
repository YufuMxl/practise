package com.mxl2020.algorithms.practise.hashtable;

/**
 * LRU 缓存
 *
 * @see <a href="https://leetcode-cn.com/problems/lru-cache/">LeetCode 146</a>
 */
public class LRUCache {

    // 缓存的最大容量
    private final int capacity;
    // 当前缓存包含的元素数量
    private int size = 0;
    // 散列表
    private final Node[] hashTable;

    // 双向链表的首尾结点字段
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        // 初始化 LRUCache 的容量大小
        this.capacity = capacity;
        this.hashTable = new Node[capacity];
        for (int i = 0; i < hashTable.length; i++) {
            // 为哈希表设置哨兵
            hashTable[i] = new Node(-1, -1);
        }

        // 同时初始化双链表的头尾
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = getNodeFromHashTable(key);
        // 返回 key 对应的 value
        // key 不存在，返回 -1
        if (node == null) return -1;
        // 特别注意，get 完成后，需要将 get 到的 node 放到链表头部
        removeFromLinkedList(node);
        addFirst(node);
        return node.value;
    }

    private Node getNodeFromHashTable(int key) {
        Node node = hashTable[hashFunction(key)];

        while (node != null) {
            if (node.key == key) {
                return node;
            } else {
                node = node.hashNext;
            }
        }
        return null;
    }

    public void put(int key, int value) {
        Node node = getNodeFromHashTable(key);

        if (node == null) {
            // key 不存在，将 key value 插入链表头部
            Node nodeToPut = new Node(key, value);
            addFirst(nodeToPut);
            // 同时将 key value 放入哈希表
            putInHashTable(nodeToPut);
            size += 1;
            if (size > capacity) {
                // 如果缓存已满，删除最后一个结点
                removeFromHashTable(tail.prev);
                removeLast();
                size -= 1;
            }
        } else {
            // 如果存在 key，则将 key 对应的结点转移至链表头部
            node.value = value;
            removeFromLinkedList(node);
            addFirst(node);
        }
    }

    private void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void putInHashTable(Node node) {
        Node nodeInHashTable = hashTable[hashFunction(node.key)];
        node.hashNext = nodeInHashTable.hashNext;
        nodeInHashTable.hashNext = node;
    }

    private void removeLast() {
        // 从链表中删除
        Node node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void removeFromHashTable(Node node) {
        // 从哈希表中删除
        Node previousNode = hashTable[hashFunction(node.key)];
        while (previousNode.hashNext != null && previousNode.hashNext.key != node.key) {
            previousNode = previousNode.hashNext;
        }
        previousNode.hashNext = node.hashNext;
        node.hashNext = null;
    }

    private void removeFromLinkedList(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class Node {
        // 健值字段
        private final int key;
        private int value;

        // 指针字段
        // prev 和 next 串联结点形成双向链表
        public Node prev;
        public Node next;
        // hashNext 串联结点形成散列表的拉链
        public Node hashNext;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 散列函数
    private int hashFunction(int key) {
        return key % hashTable.length;
    }

}
