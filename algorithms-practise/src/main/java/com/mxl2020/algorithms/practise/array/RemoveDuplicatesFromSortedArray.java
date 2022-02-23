package com.mxl2020.algorithms.practise.array;

/**
 * 删除有序数组中的重复项
 *
 * @see <a href="https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/">LeetCode 26</a>
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * 遍历数组，过滤出和前一个元素不相等的元素
     * <p>
     * 将满足条件的元素依次往数组的前面部分排放
     * <p>
     * 时间复杂度 O(n)
     * <p>
     * 空间复杂度 O(1)
     *
     * @param nums 可重复元素的有序数组
     * @return 返回去重后数组的长度
     */
    public int removeDuplicates(int[] nums) {
        // 边界判断
        if (nums.length < 2) {
            return nums.length;
        }
        int n = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[n++] = nums[i];
            }
        }
        return n;
    }
}
