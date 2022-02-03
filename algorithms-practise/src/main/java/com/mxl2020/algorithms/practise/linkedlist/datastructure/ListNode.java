package com.mxl2020.algorithms.practise.linkedlist.datastructure;

/**
 * 单链表定义
 */
public class ListNode {
    public int val;
    public ListNode next;

    private ListNode() {}

    public ListNode(int val) {this.val = val;}

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
