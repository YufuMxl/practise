package com.mxl2020.algorithms.practise.tree.disjointset;

/**
 * 省份数量
 *
 * @see <a href="https://leetcode.cn/problems/number-of-provinces/">LeetCode 547</a>
 */
public class NumberOfProvinces {

    /**
     * @param isConnected isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连；
     *                    isConnected[i][j] = 0 表示二者不直接相连
     * @return 返回省份数量
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet disjointSet = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    disjointSet.unionSet(i, j);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (disjointSet.isSelfFather(i)) ans++;
        }
        return ans;
    }

}

// TODO dfs
