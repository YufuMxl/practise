package com.mxl2020.algorithms.practise.array;

/**
 * 最大交换
 *
 * @see <a href="https://leetcode.cn/problems/maximum-swap/">LeetCode 670</a>
 */
public class MaximumSwap {

    /**
     * 解法一：遍历
     */
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        for (int i = 0; i < digits.length - 1; i++) {
            int indexOfMaxValue = i + 1;
            for (int j = i + 1; j < digits.length; j++) {
                if (digits[j] >= digits[indexOfMaxValue]) indexOfMaxValue = j;
            }
            if (digits[i] < digits[indexOfMaxValue]) {
                char tmp = digits[i];
                digits[i] = digits[indexOfMaxValue];
                digits[indexOfMaxValue] = tmp;
                return Integer.parseInt(String.valueOf(digits));
            }
        }
        return num;
    }

    /**
     * 解法二：贪心
     */
    public int maximumSwap2(int num) {
        return -1;
    }
}
