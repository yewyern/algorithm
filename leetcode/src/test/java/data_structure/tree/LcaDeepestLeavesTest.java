package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves">1123. 最深叶节点的最近公共祖先</a>
 * <a href="https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/">865. 具有所有最深节点的最小子树</a>
 *
 * @author xuzhou
 * @since 2023/9/11 18:14
 */
public class LcaDeepestLeavesTest {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return process(root, 0).node;
    }

    private TreeNodeLevel process(TreeNode node, int level) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return new TreeNodeLevel(node, level);
        }
        TreeNodeLevel left = process(node.left, level + 1);
        TreeNodeLevel right = process(node.right, level + 1);
        if (left != null && right != null) {
            return left.level == right.level ? new TreeNodeLevel(node, left.level) : (left.level > right.level ? left : right);
        }
        return left != null ? left : right;
    }

    private static class TreeNodeLevel {
        final TreeNode node;
        final int level;

        public TreeNodeLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
