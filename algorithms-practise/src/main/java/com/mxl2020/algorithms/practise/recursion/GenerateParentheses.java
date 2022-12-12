package com.mxl2020.algorithms.practise.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            List<String> aParentheses = generateParenthesis(i);
            List<String> bParentheses = generateParenthesis(n - i - 1);
            for (String aParenthesis : aParentheses) {
                for (String bParenthesis : bParentheses) {
                    ans.add("(" + aParenthesis + ")" + bParenthesis);
                }
            }
        }
        return ans;
    }

    private final List<String> ans = new ArrayList<>();
    private char[] strArr;
    private int n;

    public List<String> generateParenthesis2(int n) {
        this.n = n;
        this.strArr = new char[2 * n];
        dfs(0, 0, 0);
        return ans;
    }

    private void dfs(int index, int leftCount, int rightCount) {
        // 剪枝：1.随时判断括号数量是否符合要求 2.随时判断字符串是否合法
        if (leftCount > n || rightCount > n || !isValid()) return;
        // 终止条件
        if (index == strArr.length) {
            ans.add(String.valueOf(strArr));
            return;
        }

        strArr[index] = '(';
        dfs(index + 1, leftCount + 1, rightCount);

        strArr[index] = ')';
        dfs(index + 1, leftCount, rightCount + 1);
        strArr[index] = 0;
    }

    private boolean isValid() {
        int leftCount = 0;
        for (char c : strArr) {
            if (c == 0) break;
            if (c == '(') leftCount++;
            else {
                if (leftCount > 0) leftCount--;
                else return false;
            }
        }
        return true;
    }
}
