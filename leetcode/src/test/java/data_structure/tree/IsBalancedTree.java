package data_structure.tree;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * <p>本题中，一棵高度平衡二叉树定义为：
 * <p>
 * <p>一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 * <p>返回 true 。
 * <p>
 * <p>示例 2:
 * <p>
 * <p>给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * <p>       1
 * <p>      / \
 * <p>     2   2
 * <p>    / \
 * <p>   3   3
 * <p>  / \
 * <p> 4   4
 * <p>返回 false 。
 *
 * @author xzer
 */
public class IsBalancedTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode tree1 = TreeUtils.toTreeNodeWideFirst(3, 9, 20, null, null, 15, 7);
        TreeNode tree2 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, 3, 3, null, null, 4, 4);
        IsBalancedTree a = new IsBalancedTree();
        System.out.println("tree1 = " + a.isBalanced(tree1));
        System.out.println("tree2 = " + a.isBalanced(tree2));
    }
}