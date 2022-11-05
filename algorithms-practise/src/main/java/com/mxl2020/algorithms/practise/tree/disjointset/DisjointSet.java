package com.mxl2020.algorithms.practise.tree.disjointset;

public class DisjointSet {

    private final int[] father;

    public DisjointSet(int n) {
        father = new int[n];
        for (int i = 1; i < n; i++) {
            father[i] = i;
        }
    }

    /**
     * 查询某个元素所属集合的代表元素
     */
    public int find(int x) {
        if (father[x] == x) return x;
        return father[x] = find(father[x]);
    }

    /**
     * 将两个集合合并为一个
     */
    public void unionSet(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) father[a] = b;
    }

    /**
     * 将两个集合合并为一个，同时判断是否合并成功
     */
    public boolean tryUnionSet(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        else father[a] = b;
        return true;
    }

    /**
     * 判断某个元素是否是所属集合的代表元素
     */
    public boolean isRoot(int x) {
        return father[x] == x;
    }
}
