package com.mxl2020.algorithms.practise.hashtable;

import java.util.*;

/**
 * <p>两数之和</p>
 * <p>两数之和 II - 输入有序数组</p>
 * <p>三数之和</p>
 *
 * @see <a href="https://leetcode-cn.com/problems/two-sum/">LeetCode 1</a>
 * @see <a href="https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/">LeetCode 167</a>
 * @see <a href="https://leetcode-cn.com/problems/3sum/">LeetCode 15</a>
 */
public class TwoSum {

    /**
     * 哈希表
     * <p>
     * 时间复杂度 O(n)
     */
    public int[] twoSumI(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (numsMap.containsKey(otherNum)) {
                return new int[]{i, numsMap.get(otherNum)};
            } else {
                numsMap.put(nums[i], i);
            }
        }
        return new int[0];
    }

    /**
     * 双指针扫描
     * <p>用于解决一类基于"子段"的统计问题。子段就是数组中连续的一段</p>
     * <ol>
     * <li>这类问题的暴力解法都是双重循环的枚举，枚举左端点 l，右端点 r（l <= r）
     * <li>优化手法都是尝试去除内层循环
     * </ol>
     *
     * <p>优化策略</p>
     * <ol>
     * <li>固定右端点，看左端点的取值范围（前缀和）
     * <li>移动一个端点，看另一个端点的变化情况（滑动窗口、双指针扫描）
     * </ol>
     *
     * @param nums   排好序的数组
     * @param target 设定两数之和的结果
     * @return 返回两数之和等于 target 的数组元素下标
     */
    public int[] twoSumII(int[] nums, int target) {
        int n = nums.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) sortedNums[i] = new int[]{nums[i], i};
        Arrays.sort(sortedNums, Comparator.comparingInt(a -> a[0]));

        // 数组两头设置双指针，向中间靠拢
        int left = 0;
        int right = n - 1;
        while (sortedNums[left][0] + sortedNums[right][0] != target) {
            if (sortedNums[left][0] + sortedNums[right][0] < target) left++;
            else right--;
        }
        return new int[]{sortedNums[left][1], sortedNums[right][1]};
    }

    // 可以将 twoSumI 的数组排序之后，按照 twoSumII 求解决

    /**
     * @param nums 有重复元素的整数数组
     * @return 返回所有"三数之和为零"的三元数组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        final List<List<Integer>> ans = new ArrayList<>();
        final int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) return ans;
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }
}
