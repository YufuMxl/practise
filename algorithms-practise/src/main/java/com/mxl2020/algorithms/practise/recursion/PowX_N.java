package com.mxl2020.algorithms.practise.recursion;

/**
 * Pow(x, n)
 *
 * @see <a href="https://leetcode-cn.com/problems/powx-n/">LeetCode 50</a>
 */
public class PowX_N {

    /**
     * 分治解法
     *
     * @param x 底数
     * @param n 指数（可以是负数）
     * @return 计算 x 的 n 次幂函数，即 x^n
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == Integer.MIN_VALUE) return 1 / myPow(x, -(n + 1)) * x;
        if (n < 0) return 1 / myPow(x, -n);
        double temp = myPow(x, n / 2);
        double ans = temp * temp;
        if (n % 2 == 1) ans *= x;
        return ans;
    }
}
