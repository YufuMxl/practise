package com.mxl2020.algorithms.practise.greedy;

/**
 * 跳跃游戏 II
 *
 * @see <a href="https://leetcode-cn.com/problems/jump-game-ii/">LeetCode 45</a>
 */
public class JumpGameII {

    /**
     * 贪心策略：在能跳到的格子中，选择一个能跳得最远的格子。该策略具备决策包容性
     *
     * @param nums 非负整数数组
     * @return 返回跳到 last index 的最少次数
     */
    public int jump(int[] nums) {
        int jumpCount = 0;
        int position = 0;
        while (position < nums.length - 1) {
            position = fetchNextJumpIndex(nums, position + 1, position + nums[position]);
            jumpCount++;
        }
        return jumpCount;
    }

    private int fetchNextJumpIndex(int[] nums, int start, int end) {
        int lastIndex = nums.length - 1;
        if (end >= lastIndex) return lastIndex;
        if (start == end) return start;

        int nextJumpIndex = start;
        for (int i = start + 1; i <= end; i++) {
            nextJumpIndex = i + nums[i] > nextJumpIndex + nums[nextJumpIndex] ? i : nextJumpIndex;
        }
        return nextJumpIndex;
    }
}
