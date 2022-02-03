package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 链表的中间结点
 *
 * @see <a href="https://leetcode-cn.com/problems/middle-of-the-linked-list/">LeetCode 876</a>
 */
public class MiddleOfTheLinkedList {

    /**
     * 快慢指针
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // 同时移动 slow 和 fast 指针
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
