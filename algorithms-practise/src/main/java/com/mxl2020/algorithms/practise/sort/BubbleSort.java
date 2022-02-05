package com.mxl2020.algorithms.practise.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    // 冒泡排序，a 表示数组，n 表示数组大小
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n - 1; i++) {
            // 提前退出冒泡循环的标志位
            boolean finish = true;
            for (int j = 0; j < n - 1 - i; j++) {
                // 比较
                if (a[j] > a[j + 1]) {
                    // 交换
                    int small = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = small;
                    // 表示有数据交换
                    finish = false;
                }
            }
            // 没有数据交换，提前退出
            if (finish) break;
        }
    }
}
