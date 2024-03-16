package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/count-complete-tree-nodes">222. 完全二叉树的节点个数</a>
 * @author xuzhou
 * @since 2023/12/27 16:16
 */
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
