package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 合并两个有序链表
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">LeetCode 21</a>
 */
public class MergeTwoSortedLists {

    /**
     * 递归
     * <p>
     * 时间复杂度 O(m+n)
     * 空间复杂度 O(m+n)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 边界条件 + 终止条件
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 哨兵
     * <p>
     * 时间复杂度 O(m+n)
     * 空间复杂度 O(1)
     */
    public ListNode mergeTwoListsWithSoldier(ListNode list1, ListNode list2) {
        ListNode soldier = new ListNode(-1);
        ListNode prev = soldier;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        prev.next = list1 == null ? list2 : list1;

        return soldier.next;
    }

}
