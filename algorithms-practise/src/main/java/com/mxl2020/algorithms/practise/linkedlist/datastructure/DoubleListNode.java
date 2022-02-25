package com.mxl2020.algorithms.practise.linkedlist.datastructure;

/**
 * 双链表定义
 */
public class DoubleListNode {
    public int val;
    public int index;
    public DoubleListNode pre;
    public DoubleListNode next;

    public DoubleListNode(int val) {
        this.val = val;
    }

    public DoubleListNode(int val, int index) {
        this.val = val;
        this.index = index;
    }
}
