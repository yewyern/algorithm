package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal">106. 从中序与后序遍历序列构造二叉树</a>
 * @author xuzhou
 * @since 2023/12/27 16:26
 */
public class ConstructBinaryTreeFromPostorderAndInorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        return process(inorder, postorder, 0, 0, n);
    }

    private TreeNode process(int[] inorder, int[] postorder, int inStart, int postStart, int len) {
        if (len <= 0) {
            return null;
        }
        int postEnd = postStart + len - 1;
        TreeNode parent = new TreeNode(postorder[postEnd]);
        int l = 0;
        while (inorder[inStart + l] != postorder[postEnd]) {
            l++;
        }
        parent.left = process(inorder, postorder, inStart, postStart, l);
        parent.right = process(inorder, postorder, inStart + l + 1, postStart + l, len - l - 1);
        return parent;
    }
}
