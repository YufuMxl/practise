package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

public class KnapsackProblem {

    /**
     * 0-1 背包问题
     * <p>
     * 如果每种物品只能选 0 个或 1 个（即要么将此物品装进包里要么不装），则此问题称为 0-1 背包问题
     *
     * @param weight 物品重量
     * @param worth  物品价值
     * @param volume 背包可承载重量
     * @return 返回背包的最大装载价值
     */
    public int oneZeroKnapsackProblem(int[] weight, int[] worth, int volume) {
        // opt[i][j] 表示从前 i 个物品中选出总重量为 j 的物品的最大价值
        int[][] opt = new int[weight.length][volume + 1];
        // 确定边界
        if (weight[0] <= volume) opt[0][weight[0]] = worth[0];
        for (int i = 1; i < opt.length; i++) {
            for (int j = 1; j < opt[0].length; j++) {
                // 不选第 i 个物品时的最大价值
                opt[i][j] = opt[i - 1][j];
                // 选第 i 个物品时的最大价值
                if (j >= weight[i]) opt[i][j] = Math.max(opt[i][j], opt[i - 1][j - weight[i]] + worth[i]);
            }
        }

        int ans = 0;
        for (int j = 1; j < opt[0].length; j++) {
            ans = Math.max(ans, opt[opt.length - 1][j]);
        }
        return ans;
    }

    /**
     * 0-1 背包问题的代码优化
     */
    public int oneZeroKnapsackProblem2(int[] weight, int[] worth, int volume) {
        int[] opt = new int[volume + 1];
        if (weight[0] <= volume) opt[weight[0]] = worth[0];
        for (int i = 1; i < weight.length; i++) {
            for (int j = volume; j >= weight[i]; j--) {
                opt[j] = Math.max(opt[j], opt[j - weight[i]] + worth[i]);
            }
        }

        int ans = 0;
        for (int j = 1; j <= volume; j++) {
            ans = Math.max(ans, opt[j]);
        }
        return ans;
    }

    /**
     * 完全背包问题
     * <p>
     * 如果不限每种物品的数量，则称为无界（或完全）背包问题
     */
    public int completeKnapsackProblem(int[] weight, int[] worth, int volume) {
        // opt[i][j] 表示从前 i 种物品中选出总重量为 j 的物品的最大价值
        // j 的值不超过 volume
        int[][] opt = new int[weight.length][volume + 1];
        // 确定边界
        int firstGoodCount = 1;
        while (firstGoodCount * weight[0] <= volume) {
            opt[0][firstGoodCount * weight[0]] = firstGoodCount * worth[0];
            firstGoodCount++;
        }

        for (int i = 1; i < opt.length; i++) {
            for (int j = 1; j < opt[0].length; j++) {
                // 选择的结果不包含物品 i
                opt[i][j] = opt[i - 1][j];
                // 选择的结果包含物品 i
                if (j >= weight[i]) opt[i][j] = Math.max(opt[i][j], opt[i][j - weight[i]] + worth[i]);
            }
        }

        int ans = 0;
        for (int value : opt[opt.length - 1]) {
            ans = Math.max(ans, value);
        }
        return ans;
    }

    /**
     * 完全背包问题的代码优化
     * <p>
     * 完全与 0-1 优化后的区别在于：1.边界 2.j 的正序和逆序
     */
    public int completeKnapsackProblem2(int[] weight, int[] worth, int volume) {
        int[] opt = new int[volume + 1];
        // 确定边界
        int firstGoodCount = 1;
        while (firstGoodCount * weight[0] <= volume) {
            opt[firstGoodCount * weight[0]] = firstGoodCount * worth[0];
            firstGoodCount++;
        }

        for (int i = 1; i < weight.length; i++) {
            for (int j = weight[i]; j <= volume; j++) {
                opt[j] = Math.max(opt[j], opt[j - weight[i]] + worth[i]);
            }
        }

        int ans = 0;
        for (int value : opt) {
            ans = Math.max(ans, value);
        }
        return ans;
    }
}
