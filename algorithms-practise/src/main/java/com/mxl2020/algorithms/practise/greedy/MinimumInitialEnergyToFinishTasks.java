package com.mxl2020.algorithms.practise.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 完成所有任务的最少初始能量
 *
 * @see <a href="https://leetcode-cn.com/problems/minimum-initial-energy-to-finish-tasks/">LeetCode 1665</a>
 */
public class MinimumInitialEnergyToFinishTasks {

    /**
     * 贪心策略：启动能量和实耗能量差值大的先做（差值越大，可以留下的能量越多）
     * <p>
     * 可以用邻项交换证明该策略的正确性
     *
     * @param tasks tasks[0][1] 为 task0 的启动能量；tasks[0][0] 为 task0 的实耗能量
     * @return 返回需要完成所有任务的最少初始能量
     */
    public int minimumEffort(int[][] tasks) {
        // 按照贪心策略对 tasks 进行排序
        Arrays.sort(tasks, Comparator.comparingInt(task -> task[0] - task[1]));

        // 预设足够大的初始能量
        int initialEnergy = Integer.MAX_VALUE;
        // 任务执行过程中记录冗余能量。冗余能量 = Min(当前剩余能量 - 当前启动能量)
        int currentEnergy = initialEnergy;
        int redundantEnergy = Integer.MAX_VALUE;
        for (int[] task : tasks) {
            redundantEnergy = Math.min(redundantEnergy, currentEnergy - task[1]);
            currentEnergy -= task[0];
        }
        // 最少初始能量 = 预设能量 - 冗余能量
        return initialEnergy - redundantEnergy;
    }
}
