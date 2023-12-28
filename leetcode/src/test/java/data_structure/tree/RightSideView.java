package data_structure.tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-right-side-view">199. 二叉树的右视图</a>
 * @author xuzhou
 * @since 2023/12/28 16:58
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, 0, ans);
        return ans;
    }

    private void process(TreeNode root, int level, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (level < ans.size()) {
            ans.set(level, root.val);
        } else {
            ans.add(root.val);
        }
        process(root.left, level + 1, ans);
        process(root.right, level + 1, ans);
    }
}
