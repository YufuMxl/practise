package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 零钱兑换
 *
 * @see <a href="https://leetcode-cn.com/problems/coin-change/">LeetCode 322</a>
 */
public class CoinChange {

    /**
     * 以 coins = [10, 9, 1] 为例分析问题
     *
     * @param coins  不同面额硬币的可选集合
     * @param amount 总金额
     * @return 返回可以凑成总金额所需的最少的硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        final int INF = (int) 1e5;
        // opt = optimization
        int[] opt = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            opt[i] = INF;
            for (int coin : coins) {
                if (i - coin >= 0) opt[i] = Math.min(opt[i - coin] + 1, opt[i]);
            }
        }
        return opt[amount] == INF ? -1 : opt[amount];
    }
}
