package com.mxl2020.algorithms.practise.statusspace.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳跃游戏
 *
 * @see <a href="https://leetcode.cn/problems/jump-game/">LeetCode 55</a>
 */
public class JumpGame {

    /**
     * @param nums 非负整数数组
     * @return 判断是否能够到达 nums 的最后一个下标
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] opt = new boolean[n];
        opt[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = Math.min(i + nums[i], n - 1); j >= i + 1; j--) {
                if (opt[j]) {
                    opt[i] = true;
                    break;
                }
            }
        }

        return opt[0];
    }

    public boolean canJump2(int[] nums) {
        int n = nums.length;
        nums[n - 1] = 1;
        List<Integer> zeroIndices = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == 0) zeroIndices.add(i);
        }

        int preZeroIndex = -1;
        int maxJumpIndex = -1;
        for (int zeroIndex : zeroIndices) {
            for (int j = preZeroIndex + 1; j < zeroIndex; j++) {
                maxJumpIndex = Math.max(maxJumpIndex, j + nums[j]);
            }
            if (maxJumpIndex <= zeroIndex) return false;
            preZeroIndex = zeroIndex;
        }

        return true;
    }
}
