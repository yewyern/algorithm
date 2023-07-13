package data_structure.tree;

import utils.TreeNode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. 从前序与中序遍历序列构造二叉树</a>
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 * @author xuzhou
 * @since 2023/7/13 17:28
 */
public class BuildTreeTest {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return doBuildTree(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode doBuildTree(int[] preorder, int[] inorder, int preStart, int inStart, int len) {
        if (len == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (len == 1) {
            return root;
        }
        int l = len - 1;
        while (l >= 0 && inorder[inStart + l] != preorder[preStart]) {
            l--;
        }
        root.left = doBuildTree(preorder, inorder, preStart + 1, inStart, l);
        root.right = doBuildTree(preorder, inorder, preStart + l + 1, inStart + l + 1, len - l - 1);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) {
            return root;
        }
        int N = inorder.length, r = N - 1;
        while (r >= 0 && inorder[r] != preorder[0]) {
            r--;
        }
        if (r > 0) {
            root.left = buildTree(Arrays.copyOfRange(preorder, 1, r + 1), Arrays.copyOfRange(inorder, 0, r));
        }
        if (r < N - 1) {
            root.right = buildTree(Arrays.copyOfRange(preorder, r + 1, N), Arrays.copyOfRange(inorder, r + 1, N));
        }
        return root;
    }
}
