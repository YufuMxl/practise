package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 买卖股票的最佳时机
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/">LeetCode 121</a>
 */
public class BestTimeBuySellStock {

    /**
     * 前缀 min
     *
     * @param prices 数组下标对应日期，元素的值对应股价
     * @return 返回买卖一次能获得的最大收益（买卖不能同一天）
     */
    public int maxProfit(int[] prices) {
        int preMin = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - preMin);
            preMin = Math.min(preMin, prices[i]);
        }
        return maxProfit;
    }
}
