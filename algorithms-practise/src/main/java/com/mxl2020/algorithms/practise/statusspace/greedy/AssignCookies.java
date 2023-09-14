package com.mxl2020.algorithms.practise.statusspace.greedy;

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

        int result = 0;

        // 贪心分发饼干：给每个孩子发能满足他的最小饼干
        for (int cookie : s) {
            if (childIndex < g.length && g[childIndex] <= cookie) {
                childIndex++;
                result++;
            }
        }

        return result;
    }
}
