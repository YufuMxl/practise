package com.mxl2020.algorithms.practise.sort.template;

/**
 * 快速排序（交换排序）
 *
 * @see <a href="https://leetcode-cn.com/problems/sort-an-array/">LeetCode 912</a>
 */
public class QuickSort {

    private int[] nums;

    public int[] sortArray(int[] nums) {
        this.nums = nums;
        quickSort(0, nums.length - 1);
        return nums;
    }

    private void quickSort(int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;

        // 对数组进行分区，并返回分区点的下标
        int pivotIndex = partition(startIndex, endIndex);

        // 对分区点的左右两个分区进行排序
        quickSort(startIndex, pivotIndex - 1);
        quickSort(pivotIndex + 1, endIndex);
    }

    private int partition(int startIndex, int endIndex) {
        int randomIndex = startIndex + (int) (Math.random() * (endIndex - startIndex + 1));
        int pivotValue = nums[randomIndex];
        nums[randomIndex] = nums[endIndex];
        nums[endIndex] = pivotValue;

        // 定义游标：i 为要和 pivot 比较大小的元素，j 为可以交换位置的元素下标
        int i = startIndex;
        int j = endIndex - 1;

        // 对 startIndex ~ endIndex-1 的数组进行分区
        while (i <= j) {
            if (nums[i] <= pivotValue) i++;
            else {
                int tmp = nums[j];
                nums[j--] = nums[i];
                nums[i] = tmp;
            }
        }

        nums[endIndex] = nums[i];
        nums[i] = pivotValue;
        return i;
    }

}
