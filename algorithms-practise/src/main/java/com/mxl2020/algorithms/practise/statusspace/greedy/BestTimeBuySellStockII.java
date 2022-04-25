package com.mxl2020.algorithms.practise.statusspace.greedy;

/**
 * 买卖股票的最佳时机 II
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/">LeetCode 122</a>
 */
public class BestTimeBuySellStockII {

    /**
     * 该题解法的局部最优决策是：
     * <p>
     * 当前持有股票，如果第二天跌就卖；
     * 当前没有股票，如果第二天涨就买
     * <p>
     * 该决策的正确性显而易见
     *
     * @param prices 数组下标对应日期，元素的值对应股价
     * @return 返回最大收益
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int holdingSharePrice = 0;
        boolean isHoldingShare = false;

        for (int i = 0; i < prices.length; i++) {
            // 如果到最后一天还持有股票，就直接卖掉
            if (i == prices.length - 1) {
                if (isHoldingShare) profit += (prices[i] - holdingSharePrice);
                return profit;
            }

            // 买
            if (!isHoldingShare && prices[i] < prices[i + 1]) {
                isHoldingShare = true;
                holdingSharePrice = prices[i];
                continue;
            }

            // 卖
            if (isHoldingShare && prices[i] > prices[i + 1]) {
                isHoldingShare = false;
                profit += (prices[i] - holdingSharePrice);
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }
        return profit;
    }
}
