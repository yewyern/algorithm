package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/">114. 二叉树展开为链表</a>
 * @author xuzhou
 * @since 2023/12/27 14:26
 */
public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        process(root);
    }

    private TreeNode process(TreeNode p) {
        if (p == null) {
            return null;
        }
        TreeNode left = process(p.left);
        TreeNode right = process(p.right);
        if (left != null && right != null) {
            left.right = p.right;
            p.right = p.left;
            p.left = null;
            return right;
        }
        if (left != null) {
            p.right = p.left;
            p.left = null;
            return left;
        }
        if (right != null) {
            return right;
        }
        return p;
    }
}
