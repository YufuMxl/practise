package com.mxl2020.algorithms.practise.sort;

public class HeapSort {

    public void heapSort(int[] array) {
        int n = array.length;
        // 1. 建堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n - 1, i);
        }

        // 2. 排序
        for (int i = n - 1; i >= 1; i--) {
            int heapTopValue = array[0];
            array[0] = array[i];
            array[i] = heapTopValue;
            heapify(array, i - 1, 0);
        }
    }

    // 从上往下的堆化
    private void heapify(int[] array, int endIndex, int targetIndex) {
        int leftIndex = targetIndex * 2 + 1;
        // 递归终止条件
        if (leftIndex > endIndex) return;
        // 获取较大值的子节点的下标
        int rightIndex = leftIndex + 1;
        int maxChildIndex = rightIndex <= endIndex && array[rightIndex] > array[leftIndex] ? rightIndex : leftIndex;

        // 对比父子节点大小，进行堆化操作
        if (array[targetIndex] < array[maxChildIndex]) {
            int maxChildValue = array[maxChildIndex];
            array[maxChildIndex] = array[targetIndex];
            array[targetIndex] = maxChildValue;
            heapify(array, endIndex, maxChildIndex);
        }
    }

}
