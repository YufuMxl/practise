package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 买卖股票的最佳时机含冷冻期
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">LeetCode 309</a>
 */
public class BestTimeBuySellStockWithCooldown {

    /**
     * 这里的状态有：天数 i、持仓 j、是否在冷冻期内 l
     *
     * @param prices 数组下标对应日期，元素的值对应股价
     * @return 返回能获得的最大收益
     */
    public int maxProfit(int[] prices) {
        int[][][] opt = new int[prices.length][2][2];
        // 定义边界
        opt[0][0][1] = (int) -1e9;
        opt[0][1][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int yesterday = i - 1;
            opt[i][0][0] = Math.max(opt[yesterday][0][0], opt[yesterday][0][1]);
            opt[i][0][1] = opt[yesterday][1][0] + prices[i];
            opt[i][1][0] = Math.max(opt[yesterday][1][0], opt[yesterday][0][0] - prices[i]);
            // 持有状态下不可能是冷冻期，即 opt[i][1][1] 无意义
        }

        return Math.max(opt[prices.length - 1][0][0], opt[prices.length - 1][0][1]);
    }
}
