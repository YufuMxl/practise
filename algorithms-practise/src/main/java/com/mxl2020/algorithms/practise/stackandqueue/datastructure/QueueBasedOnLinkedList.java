package com.mxl2020.algorithms.practise.stackandqueue.datastructure;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 基于链表实现的链式队列
 */
public class QueueBasedOnLinkedList {

    private ListNode head;
    private ListNode tail;

    // 入队
    public void enqueue(int item) {
        ListNode newListNode = new ListNode(item);
        if (tail == null) {
            head = newListNode;
            tail = newListNode;
        } else {
            tail.next = newListNode;
            tail = tail.next;
        }
    }

    // 出队
    public int dequeue() {
        if (head == null) {
            return -1;
        }
        int result = head.val;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        return result;
    }
}
