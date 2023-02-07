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
        ListNode soldier = new ListNode(Integer.MIN_VALUE);
        ListNode newList = soldier;
        while (size > 0) {
            newList.next = removeHeap();
            newList = newList.next;
            if (newList.next != null) insertHeap(newList.next);
        }

        return soldier.next;
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
     * 堆的简化写法：使用 Java PriorityQueue
     */
    public ListNode mergeKListsWithPriorityQueue(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode node : lists) {
            while (node != null) {
                pq.offer(node);
                ListNode next = node.next;
                node.next = null;
                node = next;
            }
        }

        ListNode soldier = new ListNode(-1);
        ListNode newListNode = soldier;
        while (!pq.isEmpty()) {
            newListNode.next = pq.poll();
            newListNode = newListNode.next;
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
