package com.mxl2020.algorithms.practise.string;

/**
 * 找出字符串中第一个匹配项的下标
 *
 * @see <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/">LeetCode 28</a>
 */
public class FindIndexOfFirstOccurrenceInString {

    /**
     * Rabin-Karp 算法
     * O(m + n)
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m > n) return -1;

        // 定义进制和模数
        // b 取 131，p 取大质数，hash 冲突的概率极小
        int b = 131;
        int p = (int) 1e9 + 7;

        // 计算 haystack 前缀 hash
        // preHash 用于保存 haystack 前缀哈希
        long[] preHash = new long[n];
        // 加 1 的目的是让字符 a 的值为 1，因为 ab != b，但是 01 = 1，所以不能让 a = 0
        preHash[0] = (haystack.charAt(0) - 'a' + 1) % p;
        for (int i = 1; i < n; i++) {
            preHash[i] = (preHash[i - 1] * b + (haystack.charAt(i) - 'a' + 1)) % p;
        }

        // 计算 needle hash
        long hNeedle = 0;
        long powB = 1;
        for (char c : needle.toCharArray()) {
            hNeedle = (hNeedle * b + (c - 'a' + 1)) % p;
            // 对加减乘的式子在任意时刻取模，不会对结果造成影响
            // 应该及时取模；及时取模的目的是让答案在 long 的范围内
            powB = (powB * b) % p;
        }

        // 滑动窗口比较 haystack 子串和 needle 的 hash 值是否相等
        if (preHash[m - 1] == hNeedle) return 0;
        for (int l = 1; l < n - m + 1; l++) {
            int r = l + m - 1;
            // 在减法中，为了避免取模得负数，有如下写法
            if (((preHash[r] - preHash[l - 1] * powB) % p + p) % p == hNeedle) return l;
        }

        return -1;
    }

    /**
     * KMP 算法
     * O(m + n)
     */
    public int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int n = haystack.length(), m = needle.length();
        haystack = " " + haystack;
        needle = " " + needle;

        // 构造 needle 的 next 数组
        int[] next = new int[m + 1];
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j + 1)) j = next[j];
            if (needle.charAt(i) == needle.charAt(j + 1)) j++;
            next[i] = j;
        }

        // haystack 与 needle 进行匹配
        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j + 1)) j = next[j];
            if (haystack.charAt(i) == needle.charAt(j + 1)) j++;
            if (j == m) return i - j;
        }

        return -1;
    }
}
