package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 制作 m 束花所需的最少天数
 *
 * @see <a href="https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/">LeetCode 1482</a>
 */
public class MinimumDaysToMakeBouquets {

    /**
     * @param bloomDay 花朵 i 于 bloomDay[i] 天后开花
     * @param m        目标是制作 m 束花
     * @param k        制作一束花需要相邻的 k 朵花
     * @return 返回制作 m 束花的最少等待天数
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) return -1;

        this.bloomDay = bloomDay;
        this.m = m;
        this.k = k;

        // 定义答案区间
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int bloom : bloomDay) {
            left = Math.min(left, bloom);
            right = Math.max(right, bloom);
        }

        while (left < right) {
            int ans = left + ((right - left) >> 1);
            if (validate(ans)) {
                right = ans;
            } else {
                left = ans + 1;
            }
        }

        return left;
    }

    private int[] bloomDay;
    private int m;
    private int k;

    private boolean validate(int ans) {
        int bouquetNum = 0;
        int consecutive = 0;
        for (int bloom : bloomDay) {
            if (bloom <= ans) {
                if (consecutive + 1 == k) {
                    consecutive = 0;
                    bouquetNum++;
                } else consecutive++;
            } else consecutive = 0;
        }
        return bouquetNum >= m;
    }
}
