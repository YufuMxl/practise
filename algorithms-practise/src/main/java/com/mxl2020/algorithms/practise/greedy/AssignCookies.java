package com.mxl2020.algorithms.practise.greedy;

import java.util.Arrays;

/**
 * 分发饼干
 *
 * @see <a href="https://leetcode-cn.com/problems/assign-cookies/">LeetCode 455</a>
 */
public class AssignCookies {

    /**
     * @param g 所有孩子的需求数组
     * @param s 所有饼干的大小数组
     * @return 返回孩子能被满足的最大孩子数
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int childIndex = 0;
        int cookieIndex = 0;

        int result = 0;

        // 贪心分发饼干：给每个孩子发能满足他的最小饼干
        while (childIndex < g.length && cookieIndex < s.length && g[childIndex] <= s[s.length - 1]) {
            if (g[childIndex] <= s[cookieIndex++]) {
                childIndex++;
                result++;
            }
        }

        return result;
    }
}
