package com.mxl2020.algorithms.practise.tree.disjointset;

public class DisjointSet {

    private final int[] father;

    public DisjointSet(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if (father[x] == x) return x;
        return father[x] = find(father[x]);
    }

    public void unionSet(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) father[a] = b;
    }

    public boolean tryUnionSet(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        else {
            father[a] = b;
            return true;
        }
    }

    public boolean isSelfFather(int x) {
        return father[x] == x;
    }
}
