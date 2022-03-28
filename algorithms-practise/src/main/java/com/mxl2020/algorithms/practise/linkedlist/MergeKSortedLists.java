package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

/**
 * 合并 K 个升序链表
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/">LeetCode 23</a>
 */
public class MergeKSortedLists {

    /**
     * 二叉堆-小顶堆解法
     *
     * @param lists K 个升序链表
     * @return 返回合并后的升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        this.heap = new ListNode[lists.length];
        // 1.建堆
        for (ListNode node : lists) {
            if (node != null) insertHeap(node);
        }

        // 2.取堆顶元素
        ListNode solider = new ListNode(Integer.MIN_VALUE);
        ListNode newList = solider;
        while (size > 0) {
            newList.next = removeHeap();
            newList = newList.next;
            if (newList.next != null) insertHeap(newList.next);
        }

        return solider.next;
    }

    private ListNode[] heap;
    private int size;

    private void insertHeap(ListNode node) {
        heap[size++] = node;

        // heapify up
        int currentIndex = size - 1;
        int parentIndex = (currentIndex - 1) / 2;
        while (parentIndex >= 0 && heap[parentIndex].val > heap[currentIndex].val) {
            ListNode currentNode = heap[currentIndex];
            heap[currentIndex] = heap[parentIndex];
            heap[parentIndex] = currentNode;
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    private ListNode removeHeap() {
        ListNode result = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return result;
    }

    private void heapifyDown(int index) {
        int left = index * 2 + 1;
        if (left > size - 1) return;
        int right = index * 2 + 2;
        int minChildIndex = right <= size - 1 && heap[right].val < heap[left].val ? right : left;

        if (heap[index].val > heap[minChildIndex].val) {
            ListNode tmp = heap[index];
            heap[index] = heap[minChildIndex];
            heap[minChildIndex] = tmp;
            heapifyDown(minChildIndex);
        }
    }

    /**
     * 分治解法
     * <p>
     * 先合并前 k/2 个链表，再合并后 k/2 个链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length < 1) return null;
        this.lists = lists;
        return mergeKLists2(0, lists.length - 1);
    }

    private ListNode[] lists;

    public ListNode mergeKLists2(int start, int end) {
        if (start == end) return this.lists[start];
        int mid = start + ((end - start) >> 1);
        ListNode firstListNode = mergeKLists2(start, mid);
        ListNode secondListNode = mergeKLists2(mid + 1, end);
        return mergeTwoLists(firstListNode, secondListNode);
    }

    private ListNode mergeTwoLists(ListNode listA, ListNode listB) {
        ListNode soldier = new ListNode(Integer.MIN_VALUE);
        ListNode newList = soldier;

        while (listA != null && listB != null) {
            if (listA.val <= listB.val) {
                newList.next = listA;
                listA = listA.next;
            } else {
                newList.next = listB;
                listB = listB.next;
            }
            newList = newList.next;
        }
        if (listA != null) newList.next = listA;
        else if (listB != null) newList.next = listB;

        return soldier.next;
    }

}
