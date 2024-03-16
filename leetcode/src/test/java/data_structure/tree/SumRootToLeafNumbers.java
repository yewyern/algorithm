package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/sum-root-to-leaf-numbers/">129. 求根节点到叶节点数字之和</a>
 * @author xuzhou
 * @since 2023/12/27 15:22
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        return process(root, 0);
    }

    public int process(TreeNode p, int path) {
        if (p == null) {
            return 0;
        }
        path = path * 10 + p.val;
        int left = process(p.left, path);
        int right = process(p.right, path);
        if (left > 0 && right > 0) {
            return left + right;
        }
        if (left == 0 && right == 0) {
            return path;
        }
        return left > 0 ? left : right;
    }
}
