package com.mxl2020.algorithms.practise.search.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列 II
 *
 * @see <a href="https://leetcode-cn.com/problems/permutations-ii/">LeetCode 47</a>
 */
public class PermutationsII {

    private boolean[] used;
    private final ArrayList<Integer> permutation = new ArrayList<>();
    private final List<List<Integer>> permutations = new ArrayList<>();

    /**
     * @param nums 有重复元素的整数数组
     * @return 返回所有的不同排列的数组
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 为了方便排除重复排列，要先给 nums 排序
        Arrays.sort(nums);
        this.used = new boolean[nums.length];
        dfs(nums);
        return permutations;
    }

    private void dfs(int[] nums) {
        // 递归终止条件
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            permutation.add(nums[i]);
            used[i] = true;
            dfs(nums);
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }
}
