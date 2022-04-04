package com.mxl2020.algorithms.practise.sort;

/**
 * 翻转对
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-pairs/">LeetCode 493</a>
 */
public class ReversePairs {

    private int[] nums;

    public int reversePairs(int[] nums) {
        this.nums = nums;
        return reversePairs(0, nums.length - 1);
    }

    private int reversePairs(int startIndex, int endIndex) {
        if (startIndex >= endIndex) return 0;

        int midIndex = startIndex + ((endIndex - startIndex) >> 1);
        // 计算左侧数组的"翻转对"数量
        int leftPairsCount = reversePairs(startIndex, midIndex);
        // 计算右侧数组的"翻转对"数量
        int rightPairsCount = reversePairs(midIndex + 1, endIndex);
        // 计算"翻转对"总数量
        int pairsCount = leftPairsCount + rightPairsCount;
        int j = midIndex + 1;
        for (int i = startIndex; i <= midIndex; i++) {
            while (j <= endIndex && nums[i] > nums[j] * 2L) {
                j++;
            }
            pairsCount += (j - midIndex - 1);
        }
        // 计算结束后合并左右两侧数组
        merge(startIndex, endIndex, midIndex);
        return pairsCount;
    }

    private void merge(int startIndex, int endIndex, int midIndex) {
        // 申请一个临时数组
        int[] tmpArray = new int[endIndex - startIndex + 1];
        int leftArrayIndex = startIndex;
        int rightArrayIndex = midIndex + 1;
        int tmpArrayIndex = 0;

        while (leftArrayIndex <= midIndex && rightArrayIndex <= endIndex) {
            if (nums[leftArrayIndex] <= nums[rightArrayIndex]) {
                tmpArray[tmpArrayIndex++] = nums[leftArrayIndex++];
            } else {
                tmpArray[tmpArrayIndex++] = nums[rightArrayIndex++];
            }
        }
        while (leftArrayIndex <= midIndex) {
            tmpArray[tmpArrayIndex++] = nums[leftArrayIndex++];
        }
        while (rightArrayIndex <= endIndex) {
            tmpArray[tmpArrayIndex++] = nums[rightArrayIndex++];
        }
        for (int tmp : tmpArray) {
            nums[startIndex++] = tmp;
        }
    }

}
