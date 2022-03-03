package com.mxl2020.algorithms.practise.hashtable;

/**
 * LRU 缓存
 *
 * @see <a href="https://leetcode-cn.com/problems/lru-cache/">LeetCode 146</a>
 */
public class LRUCache {

    // 缓存的元素个数
    private int size = 0;
    // 缓存的最大容量
    private final int capacity;

    // 散列表
    private final Node[] hashTable;

    // 双向链表
    private final Node linkedListHead;
    private final Node linkedListTail;

    public LRUCache(int capacity) {
        // 初始化缓存容量
        this.capacity = capacity;

        // 初始化散列表
        this.hashTable = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            // 为散列表分配哨兵结点
            hashTable[i] = new Node(-1, -1);
        }

        // 初始化双向链表
        this.linkedListHead = new Node(-1, -1);
        this.linkedListTail = new Node(-1, -1);
        linkedListHead.linkedListNext = linkedListTail;
        linkedListTail.linkedListPrev = linkedListHead;
    }

    public int get(int key) {
        Node node = getFromHashTable(key);
        // node 为空，返回 -1
        if (node == null) return -1;

        // node 不为空，先将该 node 放到双向链表头部，再返回该 node 的值
        removeFromLinkedList(node);
        insertInLinkedList(linkedListHead, node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = getFromHashTable(key);
        if (node != null) {
            // 更新 node 的值，将 node 放到双向链表头部
            node.value = value;
            removeFromLinkedList(node);
            insertInLinkedList(linkedListHead, node);
        } else {
            size += 1;
            if (size > capacity) {
                // 删除链表的尾部结点
                Node tailNode = linkedListTail.linkedListPrev;
                removeFromLinkedList(tailNode);
                // 同时从散列表中删除
                removeFromHashTable(tailNode.key);
                size -= 1;
            }
            // 插入散列表和双向链表中
            Node newNode = putInHashTable(key, value);
            insertInLinkedList(linkedListHead, newNode);
        }
    }

    // 以下是双向链表操作
    private void insertInLinkedList(Node pre, Node node) {
        node.linkedListPrev = pre;
        node.linkedListNext = pre.linkedListNext;
        pre.linkedListNext.linkedListPrev = node;
        pre.linkedListNext = node;
    }

    private void removeFromLinkedList(Node node) {
        Node pre = node.linkedListPrev;
        node.linkedListNext.linkedListPrev = pre;
        pre.linkedListNext = node.linkedListNext;
        node.linkedListPrev = null;
        node.linkedListNext = null;
    }

    // 以下是散列表操作
    private Node getFromHashTable(int key) {
        Node headNode = hashTable[hash(key)];
        while (headNode.hashTableNext != null) {
            headNode = headNode.hashTableNext;
            if (headNode.key == key) {
                return headNode;
            }
        }
        return null;
    }

    private Node putInHashTable(int key, int value) {
        Node headNode = hashTable[hash(key)];
        Node node = new Node(key, value);
        node.hashTableNext = headNode.hashTableNext;
        headNode.hashTableNext = node;
        return node;
    }

    private void removeFromHashTable(int key) {
        Node headNode = hashTable[hash(key)];
        while (headNode.hashTableNext != null) {
            Node node = headNode.hashTableNext;
            if (node.key == key) {
                // 删除结点
                headNode.hashTableNext = node.hashTableNext;
                node.hashTableNext = null;
                break;
            }
            // 如果没有找到 key 对应的 node，将 head 指针向下移动
            headNode = node;
        }
    }

    static class Node {
        public final int key;
        public int value;

        public Node linkedListPrev;
        public Node linkedListNext;

        public Node hashTableNext;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(int key) {
        return key % capacity;
    }

}
