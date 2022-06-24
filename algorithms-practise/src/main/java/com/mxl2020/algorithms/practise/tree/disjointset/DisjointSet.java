package com.mxl2020.algorithms.practise.tree.disjointset;

public class DisjointSet {

    private final int[] fa;

    public DisjointSet(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    public void unionSet(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) fa[a] = b;
    }

}
