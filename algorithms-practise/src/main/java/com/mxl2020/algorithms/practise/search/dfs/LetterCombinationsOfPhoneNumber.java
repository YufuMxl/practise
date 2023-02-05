package com.mxl2020.algorithms.practise.search.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 电话号码的字母组合
 *
 * @see <a href="https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/">LeetCode 17</a>
 */
public class LetterCombinationsOfPhoneNumber {

    private final char[][] buttons = new char[][]{
            null,
            null,
            new char[]{'a', 'b', 'c'},
            new char[]{'d', 'e', 'f'},
            new char[]{'g', 'h', 'i'},
            new char[]{'j', 'k', 'l'},
            new char[]{'m', 'n', 'o'},
            new char[]{'p', 'q', 'r', 's'},
            new char[]{'t', 'u', 'v'},
            new char[]{'w', 'x', 'y', 'z'}
    };

    /**
     * @param digits 用字符串表示的按键序列（每个按键的范围是 2-9）
     * @return 根据按键序列，返回按键中字母的所有组合
     */
    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return Collections.emptyList();
        this.digits = digits.toCharArray();
        this.combination = new char[digits.length()];
        this.combinations = new ArrayList<>();

        combine(0);
        return combinations;
    }

    private char[] digits;
    private char[] combination;
    private List<String> combinations;

    private void combine(int index) {
        if (index == digits.length) {
            combinations.add(String.valueOf(combination));
            return;
        }

        for (char letter : buttons[digits[index] - '0']) {
            combination[index] = letter;
            combine(index + 1);
        }
    }
}
