package com.mxl2020.algorithms.practise.sort.template;

public class CountingSort {
    // 计数排序，a 是数组，n 是数组大小
    // 假设数组中存储的都是非负整数
    public void countingSort(int[] a) {
        int n = a.length;
        if (n <= 1) return;

        // 获取数组 A 的数据范围
        int maxValueOfArray = a[0];
        for (int i = 1; i < n; i++) {
            if (maxValueOfArray < a[i]) maxValueOfArray = a[i];
        }

        // 初始化数组 C，它的下标为 0~maxValueOfArray
        // 用于保存数组 A 中相同值的个数
        int[] c = new int[maxValueOfArray + 1];
        for (final int valueOfArray : a) {
            c[valueOfArray]++;
        }

        // 从头累加 C 的值
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }

        // 申请临时数组 R，用于存储排序之后的结果
        int[] r = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            r[c[a[i]] - 1] = a[i];
            c[a[i]]--;
        }

        // 将 R 的数据拷贝到原数组中
        System.arraycopy(r, 0, a, 0, n);
    }

}
