package com.mxl2020.algorithms.practise.sort.template;

/**
 * 简单插入排序（插入排序）
 */
public class InsertionSort {

    // 插入排序，a 表示数组，n 表示数组大小
    public void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int value = a[i];
            // 查找插入的位置
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > value) {
                    // 数据移动
                    a[j + 1] = a[j];
                } else {
                    // 插入数据
                    a[j + 1] = value;
                    break;
                }
            }
        }
    }

}
