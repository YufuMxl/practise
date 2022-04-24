package com.mxl2020.algorithms.practise.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * @see <a href="https://leetcode-cn.com/problems/4sum/">LeetCode 18</a>
 */
public class FourSum {

    /**
     * @param nums   可重复元素的整数数组
     * @param target 目标和
     * @return 返回元素之和等于 target 的所有子数组集合，任意子数组之间的内容不能重复
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        final int lastIndex = nums.length - 1;

        for (int a = 0; a < lastIndex - 2; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) continue;

            for (int b = a + 1; b < lastIndex - 1; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;

                int c = b + 1;
                int d = lastIndex;

                int abSum = nums[a] + nums[b];

                while (c < d) {
                    int sum = abSum + nums[c] + nums[d];
                    if (sum < target) c++;
                    else if (sum > target) d--;
                    else {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }
                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }
                        c++;
                        d--;
                    }
                }
            }
        }
        return ans;
    }
}
