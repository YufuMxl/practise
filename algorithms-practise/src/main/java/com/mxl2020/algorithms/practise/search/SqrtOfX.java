package com.mxl2020.algorithms.practise.search;

/**
 * x 的平方根
 *
 * @see <a href="https://leetcode-cn.com/problems/sqrtx/">LeetCode 69</a>
 */
public class SqrtOfX {

    /**
     * 二分查找思路
     * <p>
     * 时间复杂度 O(log n)
     * 空间复杂度 O(1)
     *
     * @param x 非负整数
     * @return 返回 x 的平方根的整数部分
     */
    public int mySqrt(int x) {
        int low = 0;
        int high = x;
        int squareRoot = -1;

        while (low <= high) {
            squareRoot = low + ((high - low) >> 1);
            if ((long) squareRoot * squareRoot == x) {
                break;
            } else if ((long) squareRoot * squareRoot < x) {
                low = squareRoot + 1;
            } else {
                high = squareRoot - 1;
            }
        }
        return squareRoot;
    }

    // TODO 如何精确到小数点后 6 位
}
