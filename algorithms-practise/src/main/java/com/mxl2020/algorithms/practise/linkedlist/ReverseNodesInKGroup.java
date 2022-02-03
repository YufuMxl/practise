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
        ListNode lastNodeOfPreviousGroup = soldier;
        ListNode firstNodeOfCurrentGroup;
        ListNode lastNodeOfCurrentGroup;

        while (lastNodeOfPreviousGroup.next != null) {
            // 定位每组的首尾结点
            firstNodeOfCurrentGroup = lastNodeOfPreviousGroup.next;
            lastNodeOfCurrentGroup = lastNodeOfPreviousGroup;
            // 循环 k 次得到 lastNodeOfCurrentGroup
            for (int i = 0; i < k; i++) {
                lastNodeOfCurrentGroup = lastNodeOfCurrentGroup.next;
                // 当前组内不满 k 个结点，直接返回
                if (lastNodeOfCurrentGroup == null) {
                    return soldier.next;
                }
            }

            // 反转链表
            lastNodeOfPreviousGroup.next = lastNodeOfCurrentGroup;
            myReverse(lastNodeOfCurrentGroup.next, firstNodeOfCurrentGroup, k);

            // 重新定位 lastNodeOfPreviousGroup
            lastNodeOfPreviousGroup = firstNodeOfCurrentGroup;
        }
        return soldier.next;
    }

    private void myReverse(ListNode previousNode, ListNode currentNode, int k) {
        for (int i = 0; i < k; i++) {
            ListNode nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
    }

}
