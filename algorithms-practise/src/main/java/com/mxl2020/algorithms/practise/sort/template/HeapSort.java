package com.mxl2020.algorithms.practise.sort.template;

/**
 * 堆排序（选择排序）
 */
public class HeapSort {

    public void heapSort(int[] array) {
        final int n = array.length;
        // 1. 建堆（将数组转换成大顶堆）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(array, i, n - 1);
        }

        // 2. 排序（堆顶元素与堆尾元素交换）
        for (int i = n - 1; i >= 1; i--) {
            int heapTopValue = array[0];
            array[0] = array[i];
            array[i] = heapTopValue;
            heapifyDown(array, 0, i - 1);
        }
    }

    // 从上往下的堆化
    private void heapifyDown(int[] array, int targetIndex, int endIndex) {
        int leftChildIndex = targetIndex * 2 + 1;
        int rightChildIndex = leftChildIndex + 1;
        if (leftChildIndex > endIndex) return;

        int maxChildIndex = leftChildIndex;
        if (rightChildIndex <= endIndex && array[rightChildIndex] > array[leftChildIndex])
            maxChildIndex = rightChildIndex;

        if (array[targetIndex] < array[maxChildIndex]) {
            int maxChildValue = array[maxChildIndex];
            array[maxChildIndex] = array[targetIndex];
            array[targetIndex] = maxChildValue;
            heapifyDown(array, maxChildIndex, endIndex);
        }
    }

}
