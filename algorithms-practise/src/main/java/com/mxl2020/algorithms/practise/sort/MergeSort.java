package com.mxl2020.algorithms.practise.sort;

/**
 * 归并排序
 */
public class MergeSort {

    public void mergeSort(int[] a, int n) {
        mergeSort(a, 0, n - 1);
    }

    private void mergeSort(final int[] a, final int startIndex, final int endIndex) {
        // 终止条件
        if (startIndex >= endIndex) return;

        int middleIndex = (startIndex + endIndex) / 2;
        // 对前后两数组分别排序
        mergeSort(a, startIndex, middleIndex);
        mergeSort(a, (middleIndex + 1), endIndex);
        // 合并已排序的两数组
        merge(a, startIndex, endIndex);
    }

    private void merge(final int[] a, final int startIndex, final int endIndex) {
        // 申请一个临时数组
        int[] tmpArray = new int[endIndex - startIndex + 1];
        // 定义游标：i 是前数组的游标，j 是后数组的游标，k 是临时数组的游标
        int middleIndex = (startIndex + endIndex) / 2;
        int i = startIndex;
        int j = middleIndex + 1;
        int k = 0;

        // 将两个数组的元素有序地排进临时数组
        while (i <= middleIndex || j <= endIndex) {
            if (i > middleIndex) {
                tmpArray[k++] = a[j++];
            } else if (j > endIndex) {
                tmpArray[k++] = a[i++];
            } else {
                if (a[i] < a[j]) {
                    tmpArray[k++] = a[i++];
                } else {
                    tmpArray[k++] = a[j++];
                }
            }
        }

        // 将临时数组的元素拷贝到 a 数组中
        for (int tmpIndex = 0; tmpIndex < tmpArray.length; tmpIndex++) {
            a[tmpIndex + startIndex] = tmpArray[tmpIndex];
        }
    }

}
