package com.mxl2020.algorithms.practise.recursion;

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
     * @param nums 无重复元素的整数数组
     * @return 返回所有的不同排列的数组
     */
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        permute(0, nums);
        return permutations;
    }

    /**
     * 往 permutations 数组中插入所有可能的排列数组
     */
    private void permute(int depth, int[] nums) {
        // 终止条件：当递归出界时，将排列好的数组 permutation 加入到 permutations 中
        if (depth == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            permutation.add(nums[i]);
            permute(depth + 1, nums);

            // 还原共享变量
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }

}
