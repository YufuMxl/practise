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

        // 定义游标：i 为要和 pivot 比较大小的元素，j 为可以交换位置的元素下标
        int i = startIndex;
        int j = endIndex - 1;

        // 对 startIndex ~ endIndex-1 的数组进行分区
        while (i <= j) {
            if (a[i] <= pivotValue) {
                i++;
            } else {
                int tmp = a[j];
                a[j--] = a[i];
                a[i] = tmp;
            }
        }

        a[endIndex] = a[i];
        a[i] = pivotValue;
        return i;
    }

}
