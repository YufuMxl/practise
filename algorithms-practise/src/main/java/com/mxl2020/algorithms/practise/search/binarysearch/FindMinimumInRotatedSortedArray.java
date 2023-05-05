package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 寻找旋转排序数组中的最小值
 *
 * @see <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/">LeetCode 153</a>
 */
public class FindMinimumInRotatedSortedArray {

    /**
     * @param nums 无重复元素的"旋转排序"的整数数组
     * @return 返回 nums 中的最小值
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] < nums[right]) right = mid;
            else return nums[mid];
        }

        return -1;
    }
}
