package easy;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * <p>说明: 叶子节点是指没有子节点的节点。
 * <p>
 * <p>示例: 
 * <p>给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * <p>              5
 * <p>             / \
 * <p>            4   8
 * <p>           /   / \
 * <p>          11  13  4
 * <p>         /  \      \
 * <p>        7    2      1
 * <p>返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
class HasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (sum == root.val && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode tree1 = TreeUtils.toTreeNodeWideFirst(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        HasPathSum a = new HasPathSum();
        System.out.println("tree1 = " + tree1);
        System.out.println("tree1 = " + a.hasPathSum(tree1, 22));
    }
}