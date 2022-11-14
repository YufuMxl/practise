package com.mxl2020.algorithms.practise.string;

/**
 * 最长回文子串
 *
 * @see <a href="https://leetcode.cn/problems/longest-palindromic-substring/">LeetCode 5</a>
 */
public class LongestPalindromicSubstring {

    /**
     * 普通解法
     */
    public String longestPalindrome(String s) {
        int n = s.length();

        int beginIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if ((right - left - 2) > (endIndex - beginIndex)) {
                beginIndex = left + 1;
                endIndex = right - 1;
            }
        }

        for (int i = 1; i < n; i++) {
            int left = i - 1;
            int right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if ((right - left - 2) > (endIndex - beginIndex)) {
                beginIndex = left + 1;
                endIndex = right - 1;
            }
        }

        return s.substring(beginIndex, endIndex + 1);
    }


    /**
     * 普通解法优化：二分 + 字符串哈希
     */
    public String longestPalindrome2(String s) {
        n = s.length();
        int beginIndex = 0;
        int endIndex = 0;
        int b = 131;

        // 9 的 ascii 值是 57
        // Z 的 ascii 值是 90
        // z 的 ascii 值是 122
        preH = new long[n];
        sufH = new long[n];
        powB = new long[n + 1];
        powB[0] = 1;

        preH[0] = s.charAt(0) % p;
        for (int i = 1; i < n; i++) {
            preH[i] = (preH[i - 1] * b + s.charAt(i)) % p;
            powB[i] = (powB[i - 1] * b) % p;
        }
        powB[n] = (powB[n - 1] * b) % p;

        sufH[0] = s.charAt(n - 1) % p;
        for (int i = 1; i < n; i++) {
            sufH[i] = (sufH[i - 1] * b + s.charAt(n - 1 - i)) % p;
        }

        for (int i = 0; i < n; i++) {
            int left = i - Math.min(i, n - 1 - i);
            int right = i;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (calcuH(mid, i) == calcuReverseH(i, 2 * i - mid)) {
                    if (mid == left || calcuH(mid - 1, i) != calcuReverseH(i, 2 * i - mid + 1)) {
                        if ((2 * (i - mid)) > (endIndex - beginIndex)) {
                            beginIndex = mid;
                            endIndex = 2 * i - mid;
                        }
                        break;
                    } else right = mid - 1;
                } else left = mid + 1;
            }
        }

        for (int i = 1; i < n; i++) {
            int left = i - 1 - Math.min(i - 1, n - 1 - i);
            int right = i - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (calcuH(mid, i - 1) == calcuReverseH(i, 2 * i - mid - 1)) {
                    if (mid == left || calcuH(mid - 1, i - 1) != calcuReverseH(i, 2 * i - mid)) {
                        if ((2 * (i - mid) - 1) > (endIndex - beginIndex)) {
                            beginIndex = mid;
                            endIndex = 2 * i - mid - 1;
                        }
                        break;
                    } else right = mid - 1;
                } else left = mid + 1;
            }
        }

        return s.substring(beginIndex, endIndex + 1);
    }

    private long[] preH;
    private long[] sufH;
    private long[] powB;
    private final int p = (int) 1e9 + 7;
    private int n;

    private long calcuH(int l, int r) {
        if (l == 0) return preH[r];
        return ((preH[r] - preH[l - 1] * powB[r - l + 1]) % p + p) % p;
    }

    private long calcuReverseH(int l, int r) {
        if (r == n - 1) return sufH[n - 1 - l];
        return ((sufH[n - 1 - l] - sufH[n - 2 - r] * powB[r - l + 1]) % p + p) % p;
    }
}
