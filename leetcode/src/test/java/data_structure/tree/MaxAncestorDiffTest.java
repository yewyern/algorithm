package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor">1026. 节点与其祖先之间的最大差值</a>
 *
 *
 * @author xuzhou
 * @since 2023/9/12 14:15
 */
public class MaxAncestorDiffTest {

    public int maxAncestorDiff(TreeNode root) {
        return process(root).diff;
    }

    private Diff process(TreeNode node) {
        if (node == null) {
            return null;
        }
        int val = node.val;
        if (node.left == null && node.right == null) {
            return new Diff(0, val, val);
        }
        Diff diff = merge(process(node.left), process(node.right));
        return new Diff(max(diff.diff, diff(val, diff.min), diff(val, diff.max)), Math.max(val, diff.max), Math.min(val, diff.min));
    }

    private Diff merge(Diff a, Diff b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return new Diff(Math.max(a.diff, b.diff), Math.max(a.max, b.max), Math.min(a.min, b.min));
    }

    private int diff(int a, int b) {
        return a > b ? a - b : b - a;
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private static class Diff {
        final int diff;
        final int max;
        final int min;

        public Diff(int diff, int max, int min) {
            this.diff = diff;
            this.max = max;
            this.min = min;
        }
    }
}
