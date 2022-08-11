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

    private int[] nums;
    private boolean[] used;
    private ArrayList<Integer> permutation;
    private List<List<Integer>> permutations;

    /**
     * @param nums 有重复元素的整数数组
     * @return 返回所有的不同排列的数组
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 为了方便排除重复排列，要先给 nums 排序
        Arrays.sort(nums);
        this.nums = nums;
        this.used = new boolean[nums.length];
        this.permutation = new ArrayList<>(nums.length);
        this.permutations = new ArrayList<>();
        permute();
        return permutations;
    }

    private void permute() {
        // 递归终止条件
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            // 与前一个元素对比，如果大小一样，当前元素就不进行排列
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            permutation.add(nums[i]);
            permute();

            // 还原共享变量
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }
}
