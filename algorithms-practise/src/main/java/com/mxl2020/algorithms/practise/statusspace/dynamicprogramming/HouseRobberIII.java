package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍 III
 *
 * @see <a href="https://leetcode.cn/problems/house-robber-iii/">LeetCode 337</a>
 */
public class HouseRobberIII {

    Map<TreeNode, int[]> opt = new HashMap<>();

    /**
     * @param root 小区入口；小区呈现树形结构
     * @return 返回能偷盗的最大现金数量（不能连续偷相邻的两家）
     */
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(opt.get(root)[0], opt.get(root)[1]);
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);

        // maxAmount[0] 表示不偷当前房子得到的最大金额
        // maxAmount[1] 表示偷当前房子能得到的最大金额
        int[] maxAmount = new int[2];
        maxAmount[0] = notRobCurrentHouse(node);
        maxAmount[1] = robCurrentHouse(node);
        opt.put(node, maxAmount);
    }

    private int notRobCurrentHouse(TreeNode node) {
        int amount = 0;
        if (node.left != null) {
            amount += Math.max(opt.get(node.left)[0], opt.get(node.left)[1]);
        }
        if (node.right != null) {
            amount += Math.max(opt.get(node.right)[0], opt.get(node.right)[1]);
        }
        return amount;
    }

    private int robCurrentHouse(TreeNode node) {
        int amount = node.val;
        if (node.left != null) {
            amount += opt.get(node.left)[0];
        }
        if (node.right != null) {
            amount += opt.get(node.right)[0];
        }
        return amount;
    }
}
