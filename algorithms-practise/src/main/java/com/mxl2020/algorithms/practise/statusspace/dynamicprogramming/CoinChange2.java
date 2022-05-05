package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 零钱兑换 II
 *
 * @see <a href="https://leetcode-cn.com/problems/coin-change-2/">LeetCode 518</a>
 */
public class CoinChange2 {

    /**
     * @param amount 总额
     * @param coins  不同面额的硬币，每种硬币有无限个
     * @return 返回"从 coins 中选择硬币以组成 amount"的总方案数
     */
    public int change(int amount, int[] coins) {
        int[][] opt = new int[coins.length][amount + 1];
        // 确定边界
        int firstCoinCount = 0;
        while (firstCoinCount * coins[0] <= amount) {
            opt[0][firstCoinCount * coins[0]] = 1;
            firstCoinCount++;
        }

        for (int i = 1; i < opt.length; i++) {
            for (int j = 0; j < opt[0].length; j++) {
                // 选择的结果不包含硬币 i
                opt[i][j] = opt[i - 1][j];
                // 选择的结果包含硬币 i
                if (j >= coins[i]) opt[i][j] += opt[i][j - coins[i]];
            }
        }

        return opt[opt.length - 1][amount];
    }

    public int change2(int amount, int[] coins) {
        int[] opt = new int[amount + 1];
        // 确定边界
        int firstCoinCount = 0;
        while (firstCoinCount * coins[0] <= amount) {
            opt[firstCoinCount * coins[0]] = 1;
            firstCoinCount++;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                opt[j] += opt[j - coins[i]];
            }
        }

        return opt[amount];
    }
}
