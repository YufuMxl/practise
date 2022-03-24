package com.mxl2020.algorithms.practise.binarysearch;

/**
 * 二分查找
 *
 * @see <a href="https://leetcode-cn.com/problems/binary-search/">LeetCode 704</a>
 */
public class BinarySearch {

    /**
     * 普通二分查找
     *
     * @param nums   无重复元素的升序整数数组
     * @param target 目标值
     * @return 返回目标值的下标，不存在则返回 -1
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
