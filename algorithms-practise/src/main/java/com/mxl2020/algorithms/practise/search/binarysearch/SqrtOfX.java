package com.mxl2020.algorithms.practise.search.binarysearch;

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
     * @param x 非负整数
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
                if (mid == high || (long) (mid + 1) * (mid + 1) > x) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public int mySqrt2(int x) {
        int left = 0;
        int right = x;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((long) mid * mid > x) right = mid - 1;
            else {
                if (mid == right || (long) (mid + 1) * (mid + 1) > x) return mid;
                else left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 实数二分查找
     */
    public double realSqrt(double x) {
        double left = 0;
        double right = x;

        // right - left > 1e-n 代表精确到小数点后 n 位
        while (right - left > 1e-6) {
            double mid = (left + right) / 2;
            if (mid * mid < x) {
                left = mid;
            } else if (mid * mid > x) {
                right = mid;
            }
        }
        return right;
    }
}
