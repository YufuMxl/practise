package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 两两交换链表中的节点
 *
 * @see <a href="https://leetcode-cn.com/problems/swap-nodes-in-pairs/">LeetCode 24</a>
 */
public class SwapNodesInPairs {

    /**
     * 迭代
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode swapPairs(ListNode head) {
        // 定义哨兵
        ListNode soldier = new ListNode(-1, head);
        ListNode prev = soldier;

        while (prev.next != null && prev.next.next != null) {
            ListNode node1 = prev.next;
            ListNode node2 = prev.next.next;

            // 交换
            prev.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            // 移动
            prev = node1;
        }

        return soldier.next;
    }

    /**
     * 递归
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public ListNode swapPairsRecursively(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairsRecursively(head.next.next);
        newHead.next = head;
        return newHead;
    }

    // 利用 stack，然后不断迭代链表，每次取出两个节点放入 stack 中，再从 stack 中拿出两个节点

}
