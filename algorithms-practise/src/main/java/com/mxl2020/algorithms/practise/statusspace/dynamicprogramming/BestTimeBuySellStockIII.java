package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 买卖股票的最佳时机 III
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/">LeetCode 123</a>
 */
public class BestTimeBuySellStockIII {

    /**
     * 这里的状态有：天数 i、持仓 j、交易次数 k
     *
     * @param prices 数组下标对应日期，元素的值对应股价
     * @return 最多交易两次，返回能获得的最大收益
     */
    public int maxProfit(int[] prices) {
        final int n = prices.length;
        // 确定状态 & 最优子结构：
        // opt[i][0][k] 表示第 i 天未持仓状态时，k 次交易后的手头最大资金
        // opt[i][1][k] 表示第 i 天已持仓状态时，k 次交易后的手头最大资金
        int[][][] opt = new int[n][2][3];
        // 确定边界
        opt[0][1][0] = -prices[0];
        opt[0][0][1] = (int) -1e9;
        opt[0][1][1] = (int) -1e9;
        opt[0][0][2] = (int) -1e9;

        for (int i = 1; i < n; i++) {
            int yesterday = i - 1;

            opt[i][0][1] = Math.max(opt[yesterday][0][1], opt[yesterday][1][0] + prices[i]);
            opt[i][0][2] = Math.max(opt[yesterday][0][2], opt[yesterday][1][1] + prices[i]);

            opt[i][1][0] = Math.max(opt[yesterday][1][0], opt[yesterday][0][0] - prices[i]);
            opt[i][1][1] = Math.max(opt[yesterday][1][1], opt[yesterday][0][1] - prices[i]);
        }

        return Math.max(0, Math.max(opt[n - 1][0][1], opt[n - 1][0][2]));
    }
}
