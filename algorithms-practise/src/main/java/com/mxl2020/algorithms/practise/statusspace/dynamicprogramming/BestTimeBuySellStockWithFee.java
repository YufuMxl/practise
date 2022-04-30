package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 买卖股票的最佳时机含手续费
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">LeetCode 714</a>
 */
public class BestTimeBuySellStockWithFee {

    /**
     * 由于没有交易次数限制，所以这里的状态只有天数 i 和持仓 j
     * <p>
     * 手续费是固定的，所以不作为状态。只需在卖股票时扣除手续费即可
     *
     * @param prices 数组下标对应日期，元素的值对应股价
     * @param fee    交易一次的手续费
     * @return 返回能获得的最大收益
     */
    public int maxProfit(int[] prices, int fee) {
        int[][] opt = new int[prices.length][2];
        // 确定边界
        opt[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int yesterday = i - 1;
            opt[i][0] = Math.max(opt[yesterday][0], opt[yesterday][1] + prices[i] - fee);
            opt[i][1] = Math.max(opt[yesterday][1], opt[yesterday][0] - prices[i]);
        }

        return opt[prices.length - 1][0];
    }
}
