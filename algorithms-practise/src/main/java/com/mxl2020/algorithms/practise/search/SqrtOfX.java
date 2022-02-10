package com.mxl2020.algorithms.practise.search;

/**
 * x 的平方根
 *
 * @see <a href="https://leetcode-cn.com/problems/sqrtx/">LeetCode 69</a>
 */
public class SqrtOfX {

    /**
     * 二分查找变体四
     * <p>
     * 时间复杂度 O(log n)
     * 空间复杂度 O(1)
     *
     * @return 返回 x 的平方根的整数部分
     */
    public int mySqrt(int x) {
        // 边界条件
        if (x < 0) return -1;
        if (x == 0 || x == 1) return x;
        // 设置区间
        int low = 1;
        int high = x / 2;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if ((long) mid * mid > x) {
                high = mid - 1;
            } else {
                if (mid == high || (mid + 1) * (mid + 1) > x) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    // TODO 如何精确到小数点后 6 位
    // 思路：先查找整数部分，再查找第 1 位小数，，，一直查找到第 6 位小数，得到的值 k^2<=x 即可
}
