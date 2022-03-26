package com.mxl2020.algorithms.practise.sort.template;

/**
 * 归并排序
 *
 * @see <a href="https://leetcode-cn.com/problems/sort-an-array/">LeetCode 912</a>
 */
public class MergeSort {

    private int[] nums;

    public int[] sortArray(int[] nums) {
        this.nums = nums;
        mergeSort(0, nums.length - 1);
        return this.nums;
    }

    private void mergeSort(int startIndex, int endIndex) {
        // 递归终止条件
        if (startIndex >= endIndex) return;

        int midIndex = startIndex + ((endIndex - startIndex) >> 1);
        // 对前后两数组分别排序
        mergeSort(startIndex, midIndex);
        mergeSort(midIndex + 1, endIndex);
        // 合并已排序的两数组
        merge(startIndex, endIndex, midIndex);
    }

    private void merge(int startIndex, int endIndex, int midIndex) {
        // 申请一个临时数组
        int[] tmpArray = new int[endIndex - startIndex + 1];
        int leftArrayIndex = startIndex;
        int rightArrayIndex = midIndex + 1;
        int tmpIndex = 0;

        while (leftArrayIndex <= midIndex || rightArrayIndex <= endIndex) {
            if (leftArrayIndex > midIndex) {
                tmpArray[tmpIndex++] = nums[rightArrayIndex++];
                continue;
            }
            if (rightArrayIndex > endIndex) {
                tmpArray[tmpIndex++] = nums[leftArrayIndex++];
                continue;
            }

            if (nums[leftArrayIndex] <= nums[rightArrayIndex]) {
                tmpArray[tmpIndex++] = nums[leftArrayIndex++];
            } else {
                tmpArray[tmpIndex++] = nums[rightArrayIndex++];
            }
        }

        for (int num : tmpArray) {
            nums[startIndex++] = num;
        }
    }

}
