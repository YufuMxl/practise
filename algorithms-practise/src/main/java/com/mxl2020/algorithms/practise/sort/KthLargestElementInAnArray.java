package com.mxl2020.algorithms.practise.sort;

/**
 * 查找无序数组中的第 K 个最大元素
 */
public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    private int findKthLargest(int[] a, int startIndex, int endIndex, int k) {
        if (startIndex >= endIndex) return a[startIndex];

        int pivotIndex = partition(a, startIndex, endIndex);

        if (pivotIndex + 1 == k) {
            return a[pivotIndex];
        } else if (pivotIndex + 1 > k) {
            return findKthLargest(a, startIndex, pivotIndex - 1, k);
        } else {
            return findKthLargest(a, pivotIndex + 1, endIndex, k);
        }
    }

    private int partition(final int[] a, final int startIndex, final int endIndex) {
        int pivotValue = a[endIndex];
        // 定义游标：i 用于遍历数组，j 用于表示"小区间"后一位
        int i = startIndex;
        int j = startIndex;

        // 遍历数组
        // 将 <=pivot 的值与位置 j 的元素交换，j 同时向前移动一位
        for (; i < endIndex; i++) {
            if (a[i] > pivotValue) {
                int tmp = a[j];
                a[j++] = a[i];
                a[i] = tmp;
            }
        }

        a[endIndex] = a[j];
        a[j] = pivotValue;
        return j;
    }

}
