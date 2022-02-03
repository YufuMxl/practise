package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 检测环形链表
 *
 * @see <a href="https://leetcode-cn.com/problems/linked-list-cycle/">LeetCode 141</a>
 */
public class LinkedListCycle {

    /**
     * 快慢指针（特殊的双指针）
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // 判断环的长度：快慢指针相遇后继续移动，直到第二次相遇。两次相遇间的移动次数即为环的长度

}
