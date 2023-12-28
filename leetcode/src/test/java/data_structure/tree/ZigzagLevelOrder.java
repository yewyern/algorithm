package data_structure.tree;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal">103. 二叉树的锯齿形层序遍历</a>
 * @author xuzhou
 * @since 2023/12/28 17:04
 */
public class ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        process(root, 0, ans);
        return ans;
    }

    private void process(TreeNode node, int level, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        if (level == ans.size()) {
            ans.add(new LinkedList<>());
        }
        List<Integer> values = ans.get(level);
        if ((level & 1) == 0) {
            ((LinkedList)values).addLast(node.val);
        } else {
            ((LinkedList)values).addFirst(node.val);
        }
        process(node.left, level + 1, ans);
        process(node.right, level + 1, ans);
    }
}
