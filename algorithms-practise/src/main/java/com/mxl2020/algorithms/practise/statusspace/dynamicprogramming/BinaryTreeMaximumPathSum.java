package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树中的最大路径和
 *
 * @see <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/">LeetCode 124</a>
 */
public class BinaryTreeMaximumPathSum {
    private final Map<TreeNode, int[]> map = new HashMap<>();

    /**
     * 树形动态规划
     *
     * @return 返回最大路径和
     */
    public int maxPathSum(TreeNode root) {
        map.put(null, new int[]{-(int) 1e9, -(int) 1e9, -(int) 1e9});
        dfs(root);
        return Math.max(map.get(root)[0], Math.max(map.get(root)[1], map.get(root)[2]));
    }

    private void dfs(TreeNode treeNode) {
        if (treeNode == null) return;
        dfs(treeNode.left);
        dfs(treeNode.right);

        int[] maxPathSum = new int[3];
        maxPathSum[0] = notContain(treeNode);
        maxPathSum[1] = midContain(treeNode);
        maxPathSum[2] = endContain(treeNode);
        map.put(treeNode, maxPathSum);
    }

    private int notContain(TreeNode treeNode) {
        int[] leftPathSum = map.get(treeNode.left);
        int[] rightPathSum = map.get(treeNode.right);
        int leftMaxPathSum = Math.max(leftPathSum[0], Math.max(leftPathSum[1], leftPathSum[2]));
        int rightMaxPathSum = Math.max(rightPathSum[0], Math.max(rightPathSum[1], rightPathSum[2]));
        return Math.max(leftMaxPathSum, rightMaxPathSum);
    }

    private int midContain(TreeNode treeNode) {
        int leftMaxPathSum = map.get(treeNode.left)[2];
        int rightMaxPathSum = map.get(treeNode.right)[2];
        int maxPathSum = treeNode.val;
        if (leftMaxPathSum > 0) maxPathSum += leftMaxPathSum;
        if (rightMaxPathSum > 0) maxPathSum += rightMaxPathSum;
        return maxPathSum;
    }

    private int endContain(TreeNode treeNode) {
        int maxPathSumInChild = Math.max(map.get(treeNode.left)[2], map.get(treeNode.right)[2]);
        if (maxPathSumInChild > 0) return treeNode.val + maxPathSumInChild;
        else return treeNode.val;
    }
}
