package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * K 个一组翻转链表
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-nodes-in-k-group/">LeetCode 25</a>
 */
public class ReverseNodesInKGroup {

    /**
     * 迭代
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode soldier = new ListNode(-1, head);
        ListNode nodeOfPrevGroup = soldier;

        while (nodeOfPrevGroup.next != null) {
            ListNode lastNodeOfCurrentGroup = nodeOfPrevGroup;
            for (int i = 0; i < k; i++) {
                lastNodeOfCurrentGroup = lastNodeOfCurrentGroup.next;
                if (lastNodeOfCurrentGroup == null) return soldier.next;
            }
            ListNode firstNodeOfCurrentGroup = nodeOfPrevGroup.next;
            nodeOfPrevGroup.next = lastNodeOfCurrentGroup;
            reverse(firstNodeOfCurrentGroup, lastNodeOfCurrentGroup.next, k);
            nodeOfPrevGroup = firstNodeOfCurrentGroup;
        }
        return soldier.next;
    }

    private void reverse(ListNode head, ListNode firstNodeOfNextGroup, int k) {
        ListNode previousNode = firstNodeOfNextGroup;
        for (int i = 0; i < k; i++) {
            ListNode nextNode = head.next;
            head.next = previousNode;
            previousNode = head;
            head = nextNode;
        }
    }

}
