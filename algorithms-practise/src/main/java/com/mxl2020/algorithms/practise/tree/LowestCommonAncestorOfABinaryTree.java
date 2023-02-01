package com.mxl2020.algorithms.practise.tree;

import com.mxl2020.algorithms.practise.tree.datastructure.TreeNode;
import javafx.util.Pair;

/**
 * 二叉树的最近公共祖先
 *
 * @see <a href="https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/">LeetCode 236</a>
 */
public class LowestCommonAncestorOfABinaryTree {

    /**
     * 最深的公共祖先就是最近公共祖先 LCA
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return lca;
    }

    private TreeNode lca;

    /**
     * 通过后续遍历，判断指定的节点是否包含 p 或 q
     */
    private Pair<Boolean, Boolean> dfs(TreeNode node, TreeNode p, TreeNode q) {
        // 递归终止条件
        if (node == null) return new Pair<>(false, false);
        // 递归左右子节点
        Pair<Boolean, Boolean> leftResult = dfs(node.left, p, q);
        Pair<Boolean, Boolean> rightResult = dfs(node.right, p, q);
        // 判断当前节点是否包含 p 或 q
        boolean hasP = leftResult.getKey() || rightResult.getKey() || node.val == p.val;
        boolean hasQ = leftResult.getValue() || rightResult.getValue() || node.val == q.val;
        if (hasP && hasQ && lca == null) lca = node;
        return new Pair<>(hasP, hasQ);
    }

    /*
      解法 2
      将 p 的祖先都标记为红色
      当 q 向上寻找祖先时，遇到的第一个红色标记的节点，就是 LCA
      但这种做法的问题是，一般的树结构没有给父节点指针，无法向上寻找
      可以通过额外空间存储 father 的方式解决
     */
}
