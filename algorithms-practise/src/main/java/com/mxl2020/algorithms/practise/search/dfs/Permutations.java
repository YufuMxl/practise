package com.mxl2020.algorithms.practise.search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @see <a href="https://leetcode-cn.com/problems/permutations/">LeetCode 46</a>
 */
public class Permutations {

    private boolean[] used;
    private final List<Integer> permutation = new ArrayList<>();
    private final List<List<Integer>> permutations = new ArrayList<>();

    /**
     * n! 种可能
     *
     * @param nums 无重复元素的整数数组
     * @return 返回所有的不同排列的数组
     */
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        dfs(nums);
        return permutations;
    }

    private void dfs(int[] nums) {
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            permutation.add(nums[i]);
            used[i] = true;
            dfs(nums);
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }

}
