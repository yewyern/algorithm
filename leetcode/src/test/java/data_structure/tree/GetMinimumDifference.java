package data_structure.tree;

import utils.TreeNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/minimum-absolute-difference-in-bst">530. 二叉搜索树的最小绝对差</a>
 * 本题与 783 <a href="https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/">783. 二叉搜索树节点最小距离</a> 相同
 * @author xuzhou
 * @since 2023/12/28 17:30
 */
public class GetMinimumDifference {

    int res = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        // 二叉搜索树，中序遍历是有序的，最小值一定出现在中序遍历中相邻的节点之间
        dfs(root, null);
        return res;
    }

    private TreeNode dfs(TreeNode node, TreeNode pre) {
        if (node == null) {
            return pre;
        }
        TreeNode left = dfs(node.left, pre);
        if (left != null) {
            res = Math.min(res, node.val - left.val);
        } else if (pre != null) {
            res = Math.min(res, node.val - pre.val);
        }
        return dfs(node.right, node);
    }

    public int getMinimumDifference2(TreeNode root) {
        // 中序遍历迭代写法
        int ans = Integer.MAX_VALUE;
        LinkedList<TreeNode> stack = new LinkedList<>();
        push(stack, root);
        TreeNode pre = stack.pop();
        push(stack, pre.right);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans = Math.min(ans, node.val - pre.val);
            pre = node;
            push(stack, pre.right);
        }
        return ans;
    }

    private void push(LinkedList<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

}
