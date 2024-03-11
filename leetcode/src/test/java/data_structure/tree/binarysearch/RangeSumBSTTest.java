package data_structure.tree.binarysearch;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/range-sum-of-bst">938. 二叉搜索树的范围和</a>
 * @author xuzhou
 * @since 2024/3/11 15:35
 */
public class RangeSumBSTTest {

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        // 二叉搜索树是有序的，可以直接缩减范围
        if (root.val < low) {
            ans += rangeSumBST(root.right, low, high);
        } else if (root.val > high) {
            ans += rangeSumBST(root.left, low, high);
        } else {
            ans += rangeSumBST(root.right, low, high);
            ans += rangeSumBST(root.right, low, high);
            ans += root.val >= low && root.val <= high ? root.val : 0;
        }
        return ans;
    }
}
