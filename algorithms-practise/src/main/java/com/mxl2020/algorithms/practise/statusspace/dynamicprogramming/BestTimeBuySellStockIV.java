package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机 IV
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/">LeetCode 188</a>
 */
public class BestTimeBuySellStockIV {

    /**
     * @param c      最大交易次数
     * @param prices 数组下标对应日期，元素的值对应股价
     * @return 返回能获得的最大收益
     */
    public int maxProfit(int c, int[] prices) {
        // 边界判断
        if (prices.length == 0) return 0;
        int[][][] opt = new int[prices.length][2][c + 1];
        // 确定边界
        Arrays.fill(opt[0][0], (int) -1e9);
        Arrays.fill(opt[0][1], (int) -1e9);
        opt[0][0][0] = 0;
        opt[0][1][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int yesterday = i - 1;
            for (int k = 0; k <= c; k++) {
                // 未持有状态的 0 笔交易，手头最大资金永远是 0
                if (k != 0) opt[i][0][k] = Math.max(opt[yesterday][0][k], opt[yesterday][1][k - 1] + prices[i]);
                // 已持有状态的最大交易次数为 c-1
                if (k != c) opt[i][1][k] = Math.max(opt[yesterday][1][k], opt[yesterday][0][k] - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int profit : opt[prices.length - 1][0]) {
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }
}
