package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 反转链表
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-linked-list/">LeetCode 206</a>
 */
public class ReverseLinkedList {

    /**
     * 迭代
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode reverseListIteratively(ListNode head) {
        ListNode previousNode = null;
        ListNode currentNode = head;
        ListNode nextNode;

        // 循环终止条件：当 current 为空时，停止反转
        while (currentNode != null) {
            nextNode = currentNode.next;

            // 将当前节点进行反转
            currentNode.next = previousNode;
            // previousNode 和 currentNode 前进一位
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return previousNode;
    }

    /**
     * 递归
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public ListNode reverseListRecursively(ListNode head) {
        // 边界判断
        if (head == null) {
            return null;
        }

        // 先写终止条件：head 如果是链表的最后一个节点
        if (head.next == null) {
            return head;
        }

        // 再写返回值：返回值是链表的最后一个节点
        ListNode last = reverseListRecursively(head.next);

        // 最后写业务逻辑：反转当前节点
        head.next.next = head;
        head.next = null;

        return last;
    }

    // TODO 单链表反转-妖魔化的双指针

}
