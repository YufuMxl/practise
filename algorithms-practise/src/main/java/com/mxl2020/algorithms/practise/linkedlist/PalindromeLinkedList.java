package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 回文链表
 *
 * @see <a href="https://leetcode-cn.com/problems/palindrome-linked-list/">LeetCode 234</a>
 */
public class PalindromeLinkedList {

    /**
     * 快慢指针
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public boolean isPalindrome(ListNode head) {
        // 边界条件判断
        if (head == null || head.next == null) {
            return true;
        }

        // 1.找到前半部分链表的尾结点
        ListNode slow = head;
        ListNode fast = head.next;
        // 移动快慢指针，循环结束后 slow 就是前半部分的尾结点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2.反转后半部分链表
        ListNode nextHalfSoldier = reverseList(slow.next);
        ListNode nextHalfHead = nextHalfSoldier;

        // 3.判断是否回文
        boolean result = true;
        while (nextHalfHead != null) {
            if (head.val != nextHalfHead.val) {
                result = false;
                break;
            }
            head = head.next;
            nextHalfHead = nextHalfHead.next;
        }

        // 4.恢复链表
        reverseList(nextHalfSoldier);

        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode previousNode = null;
        ListNode currentNode = head;
        ListNode nextNode;

        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return previousNode;
    }

    /**
     * TODO 递归
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public boolean isPalindromeRecursively(ListNode head) {
        return false;
    }

}
