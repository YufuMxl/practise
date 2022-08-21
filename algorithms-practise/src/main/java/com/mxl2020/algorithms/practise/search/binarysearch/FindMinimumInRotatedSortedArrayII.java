package com.mxl2020.algorithms.practise.search.binarysearch;

import java.util.ArrayList;

import static java.util.Collections.min;

/**
 * 寻找旋转排序数组中的最小值 II
 *
 * @see <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/">LeetCode 154</a>
 */
public class FindMinimumInRotatedSortedArrayII {

    /**
     * @param nums 有重复元素的"旋转排序"的整数数组
     * @return 返回 nums 中的最小值
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }
}
