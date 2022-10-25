package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.DoubleListNode;

/**
 * 加一
 *
 * @see <a href="https://leetcode-cn.com/problems/plus-one/">LeetCode 66</a>
 */
public class PlusOne {

    /**
     * 双向链表
     *
     * @param digits 用数组表示较大的数字，比如用 [1, 2, 3] 表示 123
     * @return 返回加一后的数组
     */
    public int[] plusOne(int[] digits) {
        // 新建双链表，保存 digits
        final DoubleListNode head = new DoubleListNode(-1);
        final DoubleListNode tail = new DoubleListNode(-1);
        head.next = tail;
        tail.pre = head;
        DoubleListNode preNode = head;
        for (int digit : digits) {
            DoubleListNode node = new DoubleListNode(digit);
            addNode(preNode, node);
            preNode = node;
        }

        // 从后向前遍历链表
        boolean plusOne = true;
        DoubleListNode digitNode = tail.pre;
        while (digitNode.val >= 0 && plusOne) {
            int result = digitNode.val + 1;
            if (10 == result) {
                digitNode.val = 0;
            } else {
                digitNode.val = result;
                plusOne = false;
            }
            digitNode = digitNode.pre;
        }

        // 判断是否需要补充首位
        if (head.next.val == 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        } else {
            DoubleListNode node = head.next;
            for (int i = 0; i < digits.length; i++) {
                digits[i] = node.val;
                node = node.next;
            }
        }
        return digits;
    }

    private void addNode(DoubleListNode preNode, DoubleListNode node) {
        node.pre = preNode;
        node.next = preNode.next;
        preNode.next.pre = node;
        preNode.next = node;
    }

    /**
     * 数组实现
     */
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        // 跳出 for 循环，说明数字全部是 9
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

}
