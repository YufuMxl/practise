package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和 II
 *
 * @see <a href="https://leetcode.cn/problems/path-sum-ii/">LeetCode 113</a>
 */
public class PathSumII {

    private List<List<Integer>> ans;
    private List<Integer> path;
    private int targetSum;
    private int sum;

    /**
     * @param root      二叉树的根节点
     * @param targetSum 目标和
     * @return 返回所有"从根节点到叶子节点"总和等于给定目标和的路径（注意返回的是节点值，不是节点引用）
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.ans = new ArrayList<>();
        this.path = new ArrayList<>();
        this.targetSum = targetSum;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        path.add(node.val);
        sum += node.val;
        if (sum == targetSum && node.left == null && node.right == null) ans.add(new ArrayList<>(path));

        dfs(node.left);
        dfs(node.right);

        sum -= node.val;
        path.remove(path.size() - 1);
    }
}
