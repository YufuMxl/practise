package com.mxl2020.algorithms.practise.search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 *
 * @see <a href="https://leetcode-cn.com/problems/subsets/">LeetCode 78</a>
 */
public class Subsets {

    private final List<List<Integer>> subsets = new ArrayList<>();
    private final List<Integer> chosen = new ArrayList<>();

    /**
     * 2^n 种可能
     *
     * @param nums 无重复元素的整数数组
     * @return 返回 nums 的所有子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        seekSubsets(0, nums);
        return subsets;
    }

    private void seekSubsets(int index, int[] nums) {
        // 终止条件
        if (index >= nums.length) {
            subsets.add(new ArrayList<>());
            return;
        }

        // 子问题：往 subsets 里面插入"后续子数组"的子集
        seekSubsets(index + 1, nums);

        // 处理当前元素的子集
        int numOfSubsets = subsets.size();
        for (int i = 0; i < numOfSubsets; i++) {
            List<Integer> currentSubset = new ArrayList<>(subsets.get(i));
            currentSubset.add(nums[index]);
            subsets.add(currentSubset);
        }
    }

    private void seekSubsets2(int index, int[] nums) {
        // 终止条件
        if (index == nums.length) {
            subsets.add(new ArrayList<>(chosen));
            return;
        }

        // 不含当前元素的子集
        seekSubsets2(index + 1, nums);

        // 含当前元素的子集
        chosen.add(nums[index]);
        seekSubsets2(index + 1, nums);

        // 还原共享变量
        chosen.remove(chosen.size() - 1);
    }

}
