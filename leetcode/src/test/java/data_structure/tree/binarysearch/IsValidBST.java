package data_structure.tree.binarysearch;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">98. 验证二叉搜索树</a>
 * @author xuzhou
 * @since 2023/12/28 18:24
 */
public class IsValidBST {

    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        prev = root;
        return isValidBST(root.right);
    }
}
