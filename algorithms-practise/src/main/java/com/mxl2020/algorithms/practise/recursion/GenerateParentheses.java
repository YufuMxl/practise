package com.mxl2020.algorithms.practise.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 括号生成
 *
 * @see <a href="https://leetcode-cn.com/problems/generate-parentheses/">LeetCode 22</a>
 */
public class GenerateParentheses {

    /**
     * 分治解法
     * <p>
     * 分治"划分子问题的标准"是不重不漏
     * <p>
     * 该题中，划分子问题的正确方法：(A)B
     *
     * @param n n 对括号
     * @return 返回所有有效的括号组合
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) return Collections.singletonList("");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 子问题 A
            List<String> aParentheses = generateParenthesis(i);
            // 子问题 B
            List<String> bParentheses = generateParenthesis(n - i - 1);
            // 合并子问题
            for (String aParenthesis : aParentheses) {
                for (String bParenthesis : bParentheses) {
                    ans.add("(" + aParenthesis + ")" + bParenthesis);
                }
            }
        }
        return ans;
    }
}
