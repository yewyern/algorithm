package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum">124. 二叉树中的最大路径和</a>
 * @author xuzhou
 * @since 2023/12/28 15:10
 */
public class MaxPathSum {

    int res;

    public int maxPathSum(TreeNode root) {
        res = root.val;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);
        res = Math.max(res, node.val + left + right);
        return Math.max(left, right) + node.val;
    }

    public int maxPathSum2(TreeNode root) {
        return process(root).maxPath;
    }

    private DataNode process(TreeNode treeNode) {
        if (treeNode == null) {
            return new DataNode(Integer.MIN_VALUE, 0);
        }
        DataNode leftData = process(treeNode.left);
        DataNode rightData = process(treeNode.right);
        int maxSingle = max(leftData.maxSingle, rightData.maxSingle) + treeNode.val;
        int maxPath = max(leftData.maxPath, rightData.maxPath, treeNode.val + leftData.maxSingle + rightData.maxSingle);
        return new DataNode(maxPath, max(maxSingle, 0));
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private int max(int a, int b, int c) {
        return max(a, max(b, c));
    }

    private static class DataNode {
        int maxPath;
        int maxSingle;

        public DataNode(int maxPath, int maxSingle) {
            this.maxPath = maxPath;
            this.maxSingle = maxSingle;
        }
    }
}
