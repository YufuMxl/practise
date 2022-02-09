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
        // 设置区间
        int low = 0;
        int high = x;
        // 初始化中间值
        int mid = -1;

        while (low <= high) {
            // 寻找 low~high 区间的中间数
            mid = low + ((high - low) >> 1);
            if ((long) mid * mid == x) {
                return mid;
            } else if ((long) mid * mid < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if ((long) mid * mid > x) {
            // 修正 mid 值
            mid -= 1;
        }
        return mid;
    }

    // TODO 如何精确到小数点后 6 位
    // 思路：先查找整数部分，再查找第 1 位小数，，，一直查找到第 6 位小数，得到的值 k^2<=x 即可
}
