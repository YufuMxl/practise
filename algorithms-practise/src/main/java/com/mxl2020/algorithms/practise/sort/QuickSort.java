package com.mxl2020.algorithms.practise.sort;

/**
 * 快速排序
 */
public class QuickSort {

    public void quickSort(int[] a, int n) {
        quickSort(a, 0, n - 1);
    }

    private void quickSort(final int[] a, final int startIndex, final int endIndex) {
        if (startIndex >= endIndex) return;

        // 对数组进行分区，并返回分区点的下标
        int pivotIndex = partition(a, startIndex, endIndex);

        // 对分区点的左右两个分区进行排序
        quickSort(a, startIndex, pivotIndex - 1);
        quickSort(a, pivotIndex + 1, endIndex);
    }

    private int partition(final int[] a, final int startIndex, final int endIndex) {
        int pivotValue = a[endIndex];
        int pivotIndex = endIndex;

        // 进行分区

        return pivotIndex;

    }

}
