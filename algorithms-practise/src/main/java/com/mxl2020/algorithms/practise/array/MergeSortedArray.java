package com.mxl2020.algorithms.practise.array;

/**
 * 合并两个有序数组
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-sorted-array/">LeetCode 88</a>
 */
public class MergeSortedArray {

    /**
     * 从后向前遍历数组
     * <p>
     * 时间复杂度 O(m+n)
     * <p>
     * 空间复杂度 O(1)
     *
     * @param nums1 作为 merge 后的数组，且 nums1.length == m + n
     * @param m     nums1 的元素个数
     * @param nums2 被 merge 的数组
     * @param n     nums2 的元素个数
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;      // nums1 的下标
        int j = n - 1;      // nums2 的下标
        int k = m + n - 1;  // nums1 数组的元素插入位置
        while (k >= 0 && j >= 0) {
            if (i >= 0 && nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

}
