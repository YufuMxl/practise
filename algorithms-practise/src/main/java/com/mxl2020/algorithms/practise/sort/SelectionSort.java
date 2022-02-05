package com.mxl2020.algorithms.practise.sort;

/**
 * 选择排序
 */
public class SelectionSort {

    public void selectionSort(int[] a, int n) {
        if (n <= 1) return;

        // i 代表未排序区间的首位元素的下标
        for (int i = 0; i < n - 1; i++) {
            int headValue = a[i];
            int minValue = a[i];
            int minIndex = i;
            // 进行比较，得到未排序区间中最小的元素
            for (int j = i + 1; j < n; j++) {
                if (a[j] < minValue) {
                    minValue = a[j];
                    minIndex = j;
                }
            }
            // 将未排序区间的最小元素与首位元素交换位置
            a[i] = minValue;
            a[minIndex] = headValue;
        }
    }
}
