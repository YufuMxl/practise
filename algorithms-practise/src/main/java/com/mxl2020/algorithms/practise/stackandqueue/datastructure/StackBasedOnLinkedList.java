package com.mxl2020.algorithms.practise.stackandqueue.datastructure;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 基于链表实现的链式栈
 */
public class StackBasedOnLinkedList {
    private ListNode top;

    /**
     * 入栈操作
     */
    public void push(int item) {
        top = new ListNode(item, top);
    }

    /**
     * 出栈操作
     */
    public int pop() {
        if (top == null) {
            return -1;
        }

        int result = top.val;
        top = top.next;
        return result;
    }

}
