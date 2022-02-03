package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II
 *
 * @see <a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/">LeetCode 142</a>
 */
public class LinkedListCycleII {

    /**
     * 哈希表
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> nodes = new HashSet<>();
        while (pos != null) {
            if (nodes.contains(pos)) {
                return pos;
            } else {
                nodes.add(pos);
                pos = pos.next;
            }
        }
        return null;
    }

    /**
     * 快慢指针
     * <p>
     * 1.假设环总长为 b+c；环外长度为 a；slow 在环内走了距离 b；fast 在环内走了 n 圈；
     * <p>
     * 2.fast 走的距离是 a+n(b+c)+b；slow 走的距离是 a+b
     * <p>
     * 3.fast 走了两倍的 slow 距离
     * <p>
     * 4.由 2 和 3 得到等式：a=n(b+c)-b；即当 slow 再走 a 的距离，就到了环的起点
     * <p>
     * 总结：ptr 指向 head；当 fast 与 slow 相遇后，ptr 和 slow 同时走，最终会在环的起点相遇
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode detectCycleWithSlowAndFast(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            // 移动 slow 和 fast
            slow = slow.next;
            fast = fast.next.next;

            // slow 和 fast 相遇
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        // 链表无环，return null
        return null;
    }
}
