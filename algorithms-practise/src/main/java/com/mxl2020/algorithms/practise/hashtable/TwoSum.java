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
     * @param numbers 排好序的数组
     * @param target  设定两数之和的结果
     * @return 返回两数之和等于 target 的数组元素下标
     */
    public int[] twoSumII(int[] numbers, int target) {
        // 数组两头设置双指针，向中间靠拢
        int i = 0;
        int j = numbers.length - 1;
        while (i < j && numbers[i] + numbers[j] != target) {
            if (numbers[i] + numbers[j] < target) i++;
            else j--;
        }
        return new int[]{i + 1, j + 1};
    }

    // 可以将 twoSumI 的数组排序之后，按照 twoSumII 求解决

    /**
     * @param nums 有重复元素的整数数组
     * @return 返回所有"三数之和为零"的三元数组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            List<int[]> jks = twoSumIII(nums, i + 1, -nums[i]);
            for (int[] jk : jks) {
                List<Integer> triplets = new ArrayList<>(3);
                triplets.add(nums[i]);
                triplets.add(jk[0]);
                triplets.add(jk[1]);
                result.add(triplets);
            }
        }
        return result;
    }

    private List<int[]> twoSumIII(int[] sortedNums, int startIndex, int target) {
        int i = startIndex;
        int j = sortedNums.length - 1;
        List<int[]> result = new ArrayList<>();
        while (i < j) {
            // 去重
            if (i != startIndex && sortedNums[i] == sortedNums[i - 1]) {
                i++;
                continue;
            }
            if (j != sortedNums.length - 1 && sortedNums[j] == sortedNums[j + 1]) {
                j--;
                continue;
            }

            if (sortedNums[i] + sortedNums[j] < target) {
                i++;
            } else if (sortedNums[i] + sortedNums[j] > target) {
                j--;
            } else {
                result.add(new int[]{sortedNums[i], sortedNums[j]});
                i++;
            }
        }
        return result;
    }
}
