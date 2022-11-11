package com.mxl2020.algorithms.practise.string;

/**
 * 重复叠加字符串匹配
 *
 * @see <a href="https://leetcode.cn/problems/repeated-string-match/">LeetCode 686</a>
 */
public class RepeatedStringMatch {

    public int repeatedStringMatch(String a, String b) {
        int leftCount = (b.length() - 1) / a.length() + 1;
        int rightCount = 2 * leftCount;
        // 通过二分答案确定叠加次数
        while (leftCount <= rightCount) {
            int midCount = leftCount + ((rightCount - leftCount) >> 1);
            // 通过 Rabin-Karp 算法，确定是否有子串
            if (isSubString(genStr(a, midCount), b)) {
                if (midCount == leftCount || !isSubString(genStr(a, midCount - 1), b)) return midCount;
                else rightCount = midCount - 1;
            } else leftCount = midCount + 1;
        }
        return -1;
    }

    private boolean isSubString(String strA, String strB) {
        int m = strA.length();
        int n = strB.length();
        // 定义进制和模数
        int b = 131;
        int p = (int) 1e9 + 7;

        // 计算 strA 的前缀 hash
        long[] preHash = new long[m];
        preHash[0] = strA.charAt(0) - 'a' + 1;
        for (int i = 1; i < m; i++) {
            preHash[i] = ((preHash[i - 1] * b) + (strA.charAt(i) - 'a' + 1)) % p;
        }

        // 计算 strB 的哈希
        long strBHash = 0;
        long powB = 1;
        for (char c : strB.toCharArray()) {
            strBHash = ((strBHash * b) + (c - 'a' + 1)) % p;
            powB = (powB * b) % p;
        }

        // 通过滑动窗口判断 strB 的子串的 hash 值是否有等于 strB 的哈希值
        if (preHash[n - 1] == strBHash) return true;
        for (int l = 1; l < m - n + 1; l++) {
            int r = l + n - 1;
            if (((preHash[r] - preHash[l - 1] * powB) % p + p) % p == strBHash) return true;
        }
        return false;
    }

    private String genStr(String a, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(a);
        }
        return sb.toString();
    }
}
