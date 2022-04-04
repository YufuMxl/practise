package com.mxl2020.algorithms.practise.sort;

/**
 * 数组中的第 K 个最大元素
 *
 * @see <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/">LeetCode 215</a>
 */
public class KthLargestElementInAnArray {

    private int k;
    private int[] nums;

    public int findKthLargest(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        return findKthLargest(0, nums.length - 1);
    }

    /**
     * 快速选择算法
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(log n)
     */
    private int findKthLargest(int startIndex, int endIndex) {
        if (startIndex >= endIndex) return nums[startIndex];

        int pivotIndex = partition(startIndex, endIndex);

        if (pivotIndex + 1 == k) {
            return nums[pivotIndex];
        } else if (pivotIndex + 1 > k) {
            return findKthLargest(startIndex, pivotIndex - 1);
        } else {
            return findKthLargest(pivotIndex + 1, endIndex);
        }
    }

    private int partition(final int startIndex, final int endIndex) {
        // 随机选择一个分区点，将该分区点与末尾元素交换
        int randomIndex = startIndex + (int) (Math.random() * (endIndex - startIndex + 1));
        int pivotValue = nums[randomIndex];
        nums[randomIndex] = nums[endIndex];
        nums[endIndex] = pivotValue;
        // 定义游标
        int i = startIndex;
        int j = endIndex - 1;
        while (i <= j) {
            if (nums[i] >= pivotValue) i++;
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

    // TODO 基于优先队列/堆排序的选择方法

}
