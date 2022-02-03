package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 删除链表的倒数第 n 个结点
 *
 * @see <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/">LeetCode 19</a>
 */
public class RemoveNthNodeFromEndOfList {

    /**
     * 双指针
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 定义哨兵，指向 head 结点
        ListNode soldier = new ListNode(-1, head);
        ListNode prev = soldier;

        // 将 head 移动 n 次
        for (int i = 0; i < n; i++) {
            // 边界条件判断，当 n 超出链表长度后，直接返回链表即可
            if (head == null) {
                return soldier.next;
            }
            head = head.next;
        }

        // 开始同时移动 head 和 prev
        while (head != null) {
            head = head.next;
            prev = prev.next;
        }

        // 删除 prev 后的节点即可
        prev.next = prev.next.next;

        return soldier.next;
    }

    // 在实际面试中，对于「是否需要释放被删除节点对应的空间」这一问题，需要和面试官进行沟通以达成一致

}
