package com.mxl2020.algorithms.practise.heap;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并 K 个升序链表
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/">LeetCode 23</a>
 */
public class MergeKSortedLists {

    private ListNode[] heap;
    private int size;

    /**
     * 二叉堆：使用自制小顶堆
     *
     * @param lists K 个升序链表
     * @return 返回合并后的升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        this.heap = new ListNode[lists.length];
        for (ListNode node : lists) {
            if (node != null) offer(node);
        }

        ListNode soldier = new ListNode(Integer.MIN_VALUE);
        ListNode newList = soldier;
        while (!isEmpty()) {
            newList.next = poll();
            newList = newList.next;
            if (newList.next != null) offer(newList.next);
        }

        return soldier.next;
    }

    private void offer(ListNode node) {
        heap[size++] = node;
        // 插入元素，要向上堆化 heapify up
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

    private ListNode poll() {
        ListNode result = heap[0];
        heap[0] = heap[--size];
        // 取堆顶元素，要向下堆化 heapify down
        heapifyDown(0);
        return result;
    }

    private void heapifyDown(int index) {
        int left = index * 2 + 1;
        if (left > size - 1) return;
        int right = index * 2 + 2;
        int minChildIndex = right <= size - 1 && heap[right].val < heap[left].val ? right : left;

        if (heap[index].val <= heap[minChildIndex].val) return;
        ListNode tmp = heap[index];
        heap[index] = heap[minChildIndex];
        heap[minChildIndex] = tmp;
        heapifyDown(minChildIndex);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * 二叉堆：使用 java.util.PriorityQueue
     */
    public ListNode mergeKListsWithPriorityQueue(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode soldier = new ListNode(-1);
        ListNode newListNode = soldier;
        while (!pq.isEmpty()) {
            newListNode.next = pq.poll();
            newListNode = newListNode.next;
            if (newListNode.next != null) pq.offer(newListNode.next);
        }
        return soldier.next;
    }

    /**
     * 分治解法
     * <p>
     * 先合并前 k/2 个链表，再合并后 k/2 个链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        int mid = start + ((end - start) >> 1);
        ListNode nodeA = mergeLists(lists, start, mid);
        ListNode nodeB = mergeLists(lists, mid + 1, end);
        return mergeTwoLists(nodeA, nodeB);
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
