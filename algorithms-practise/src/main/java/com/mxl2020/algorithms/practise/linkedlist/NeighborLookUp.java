package com.mxl2020.algorithms.practise.linkedlist;

import com.mxl2020.algorithms.practise.linkedlist.datastructure.DoubleListNode;

/**
 * <h2>邻值查找
 * <p>题目条件</p>
 * <ol>
 * <li>给定一个长度为 n 的数组 int[] a，a 中的数各不相同
 * <li>给定下标 i、j，满足 0 <= j < i < n
 * </ol>
 * <p>题目要求</p>
 * <ol>
 * <li>找到令 a[i] 与 a[j] 差值最小的 j
 * <li>若满足要求的 a[j] 不唯一，取令 a[j] 最小的那个 j
 * </ol>
 * <p>输出</p>
 * <ol>
 * <li>遍历数组，打印 n-1 行信息，每行信息包括：
 * <li>打印 abs(a[i] - a[j])
 * <li>打印 j
 * </ol>
 * <p>关键点</p>
 * <ol>
 * <li>按数值排序，建立有序双链表；结点同时保存值对应的下标
 * <li>建立额外一个数组，保存链表结点的指针，用于随机访问结点
 * <li>倒序遍历新数组：1.查找当前结点的前驱、后继结点；2.在链表中删除当前结点
 * </ol>
 */
public class NeighborLookUp {

    public static void main(String[] args) {
        // 输入参数
        final int[] a = new int[]{5, 4, 7, 0, 3, 9, 2, 8};
        final int n = a.length;

        // 新建一个数组，保存链表结点的指针
        final DoubleListNode[] arrayOfNode = new DoubleListNode[n];
        for (int i = 0; i < n; i++) {
            arrayOfNode[i] = new DoubleListNode(a[i], i);
        }
        // 对 arrayOfNode 按值排序
        quickSort(arrayOfNode, 0, arrayOfNode.length - 1);

        // 连接各个结点，形成有序的双链表
        final DoubleListNode head = new DoubleListNode(-1, -1);
        final DoubleListNode tail = new DoubleListNode(-1, -1);
        head.next = tail;
        tail.pre = head;
        DoubleListNode preNode = head;
        for (DoubleListNode node : arrayOfNode) {
            addNode(preNode, node);
            preNode = node;
        }

        // 重新组织 arrayOfNode
        DoubleListNode node = head.next;
        while (node.next != null) {
            arrayOfNode[node.index] = node;
            node = node.next;
        }

        for (int i = n - 1; i > 0; i--) {
            DoubleListNode minNeighborNode = getMinNeighborNode(arrayOfNode[i]);
            removeNode(arrayOfNode[i]);
            System.out.println(Math.abs(arrayOfNode[i].val - minNeighborNode.val) + " " + minNeighborNode.index);
        }
    }

    // 快速排序
    private static void quickSort(DoubleListNode[] arrayOfNode, int startIndex, int endIndex) {
        // 递归终止条件
        if (startIndex >= endIndex) return;
        // 对数组进行分区，同时获取到分区点的下标
        int pivotIndex = partition(arrayOfNode, startIndex, endIndex);
        // 递归排序左右两部分数组
        quickSort(arrayOfNode, startIndex, pivotIndex - 1);
        quickSort(arrayOfNode, pivotIndex + 1, endIndex);
    }

    // 分区函数
    private static int partition(DoubleListNode[] arrayOfNode, int startIndex, int endIndex) {
        // 随机选取一个下标，将该下标元素与末尾元素交换
        // 将该元素作为分区点/基准点
        int randomIndex = startIndex + (int) (Math.random() * (endIndex - startIndex + 1));
        DoubleListNode pivot = arrayOfNode[randomIndex];
        arrayOfNode[randomIndex] = arrayOfNode[endIndex];
        arrayOfNode[endIndex] = pivot;

        // 对数组进行分区
        int i = startIndex;
        int j = endIndex - 1;
        while (i <= j) {
            if (arrayOfNode[i].val <= pivot.val) {
                i++;
            } else {
                DoubleListNode tmp = arrayOfNode[i];
                arrayOfNode[i] = arrayOfNode[j];
                arrayOfNode[j--] = tmp;
            }
        }
        arrayOfNode[endIndex] = arrayOfNode[i];
        arrayOfNode[i] = pivot;
        return i;
    }

    // 双链表插入模版，将 node 插入到 preNode 之后
    private static void addNode(DoubleListNode preNode, DoubleListNode node) {
        node.pre = preNode;
        node.next = preNode.next;
        preNode.next.pre = node;
        preNode.next = node;
    }

    // 双链表删除模版
    private static void removeNode(DoubleListNode node) {
        if (node.index == -1) return;
        node.next.pre = node.pre;
        node.pre.next = node.next;
        node.pre = null;
        node.next = null;
    }

    private static DoubleListNode getMinNeighborNode(DoubleListNode node) {
        DoubleListNode left = node.pre;
        DoubleListNode right = node.next;
        if (left.index == -1) {
            return right;
        } else if (right.index == -1) {
            return left;
        } else {
            if (Math.abs(node.val - left.val) > Math.abs(node.val - right.val)) {
                return right;
            } else {
                return left;
            }
        }
    }
}
