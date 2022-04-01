package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 寻找峰值
 *
 * @see <a href="https://leetcode-cn.com/problems/find-peak-element/">LeetCode 162</a>
 */
public class FindPeakElement {

    /**
     * 三分查找
     *
     * @param nums 非空整数数组
     * @return 返回任一峰值元素
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 连续的两个下标的值不可能相等
            int lMid = left + ((right - left) >> 1);
            int rMid = lMid + 1;
            if (nums[lMid] < nums[rMid]) {
                left = rMid;
            } else if (nums[lMid] > nums[rMid]) {
                right = lMid;
            }
        }
        return left;
    }
}
